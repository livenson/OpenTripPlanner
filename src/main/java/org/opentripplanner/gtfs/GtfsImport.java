package org.opentripplanner.gtfs;

import org.onebusaway.csv_entities.schema.DefaultEntitySchemaFactory;
import org.onebusaway.gtfs.impl.GtfsRelationalDaoImpl;
import org.onebusaway.gtfs.model.Trip;
import org.onebusaway.gtfs.serialization.GtfsEntitySchemaFactory;
import org.onebusaway.gtfs.serialization.GtfsReader;
import org.onebusaway.gtfs.services.GtfsMutableRelationalDao;
import org.opentripplanner.graph_builder.module.GtfsFeedId;
import org.opentripplanner.model.TripExtension;

import java.io.File;
import java.io.IOException;

/** This class is used for reading gtfs files that are used for tests. */
public class GtfsImport {


    private GtfsFeedId feedId = null;

    private GtfsRelationalDaoImpl dao = null;

    public GtfsImport(File path) throws IOException {
        GtfsReader reader = new GtfsReader();
        reader.setInputLocation(path);
        setTripExtension(reader);
        readFeedId(reader);
        readDao(reader);
    }

    private void setTripExtension(GtfsReader reader) {
        DefaultEntitySchemaFactory factory = GtfsEntitySchemaFactory.createEntitySchemaFactory();
        factory.addExtension(Trip.class, TripExtension.class);
        reader.setEntitySchemaFactory(factory);
    }

    public GtfsMutableRelationalDao getDao() {
        return dao;
    }

    public GtfsFeedId getFeedId() {
        return feedId;
    }


    /* private methods */

    private void readDao(GtfsReader reader) throws IOException {
        dao = new GtfsRelationalDaoImpl();
        reader.setEntityStore(dao);
        reader.setDefaultAgencyId(getFeedId().getId());
        reader.run();
    }

    private void readFeedId(GtfsReader reader) {
        feedId = new GtfsFeedId.Builder().fromGtfsFeed(reader.getInputSource()).build();
    }

}
