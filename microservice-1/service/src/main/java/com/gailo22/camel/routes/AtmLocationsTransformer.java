package com.gailo22.camel.routes;

import com.backbase.location.rest.spec.v1.locations.Address;
import com.backbase.location.rest.spec.v1.locations.Coordinates;
import com.backbase.location.rest.spec.v1.locations.Location;
import com.openbankproject.api.model.ATM;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AtmLocationsTransformer {

    public Location transformAtmToLocation(ATM atm) {
        Location location = new Location();
        location.setId(atm.getATMID());
        location.setName(atm.getSiteName());
        location.setType("ATM");

        if (atm.getGeographicLocation() != null) {
            Coordinates coord = new Coordinates();
            coord.setLatitude(new BigDecimal(atm.getGeographicLocation().getLatitude()));
            coord.setLongitude(new BigDecimal(atm.getGeographicLocation().getLongitude()));
            location.setCoordinates(coord);
        }

        if (atm.getAddress() != null) {
            Address address = new Address();
            address.setNameOrNumber(atm.getAddress().getBuildingNumberOrName());
            address.setStreet(atm.getAddress().getStreetName());
            address.setTown(atm.getAddress().getTownName());
            address.setCountry(atm.getAddress().getCountry());
            address.setPostcode(atm.getAddress().getPostCode());
            location.setAddress(address);
        }

        return location;
    }
}