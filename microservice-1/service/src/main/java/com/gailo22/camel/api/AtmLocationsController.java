package com.gailo22.camel.api;

import com.backbase.location.rest.spec.v1.locations.LocationsApi;
import com.backbase.location.rest.spec.v1.locations.LocationsGetResponseBody;
import com.gailo22.camel.routes.AtmLocationsRoute;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AtmLocationsController implements LocationsApi {

    @EndpointInject(uri = AtmLocationsRoute.DIRECT_GET)
    private ProducerTemplate atmLocationsRoute;

    @Override
    public LocationsGetResponseBody getLocations(
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse) {
        return (LocationsGetResponseBody) atmLocationsRoute.requestBody(null);
    }

}