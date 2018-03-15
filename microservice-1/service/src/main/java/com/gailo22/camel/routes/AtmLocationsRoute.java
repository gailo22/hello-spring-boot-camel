package com.gailo22.camel.routes;

import com.openbankproject.api.spec.ATMApi;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AbstractListAggregationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.camel.language.spel.SpelExpression.spel;

@Component
public class AtmLocationsRoute extends RouteBuilder {

    public static final String DIRECT_GET = "direct:/atms/get";

    @Autowired
    private ATMApi atmApi;

    @Autowired
    private AtmLocationsTransformer atmLocationsTransformer;

    @Override
    public void configure() throws Exception {
        from(DIRECT_GET)
                .routeId("com.backbase.training.atms.get")
                // Invoke the /atms API from the Open Bank Project
                .bean(atmApi, "atmsGet(null, null)")
                // Unwrap the list of ATMs from the response wrapper
                .setBody(spel("#{body.data}"))
                .log(LoggingLevel.INFO, "${body.size} ATM locations retrieved")
                // Transform each element in parallel
                .split(body(), new GroupedBodyAggregationStrategy()).parallelProcessing()
                .bean(atmLocationsTransformer, "transformAtmToLocation(${body})")
                .end()
                // Wrap the list of Locations into a response wrapper
                .setBody(spel("#{new com.backbase.location.rest.spec.v1.locations.LocationsGetResponseBody().withLocations(body)}"));
    }

    public class GroupedBodyAggregationStrategy extends AbstractListAggregationStrategy<Object> {
        @Override
        public Object getValue(Exchange exchange) {
            return exchange.getIn().getBody();
        }
    }
}