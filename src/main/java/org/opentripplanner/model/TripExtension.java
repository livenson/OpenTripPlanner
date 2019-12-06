package org.opentripplanner.model;

import org.onebusaway.csv_entities.schema.annotations.CsvField;

public class TripExtension {

    public TripExtension() {
    }

    public TripExtension(String tripLongName) {
        this.tripLongName = tripLongName;
    }

    @CsvField(
            name = "trip_long_name",
            optional = true
    )
    private String tripLongName;

    public String getTripLongName() { return tripLongName; }

    public void setTripLongName(String tripLongName) { this.tripLongName = tripLongName; }
}
