package org.opentripplanner.model;

import org.onebusaway.csv_entities.schema.annotations.CsvField;

public class TripExtension {

    @CsvField(optional = true)
    private String tripLongName;

    public String getTripLongName() { return tripLongName; }
    public void setTripLongName(String tripLongName) { this.tripLongName = tripLongName; }
}
