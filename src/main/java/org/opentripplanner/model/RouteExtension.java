package org.opentripplanner.model;

import org.onebusaway.csv_entities.schema.annotations.CsvField;

public class RouteExtension {


    public RouteExtension() {
    }

    public RouteExtension(String competentAuthority) {
        this.competentAuthority = competentAuthority;
    }

    @CsvField(
            name = "competent_authority",
            optional = true
    )
    private String competentAuthority;

    public String getCompetentAuthority() {
        return competentAuthority;
    }

    public void setCompetentAuthority(String competentAuthority) {
        this.competentAuthority = competentAuthority;
    }

}
