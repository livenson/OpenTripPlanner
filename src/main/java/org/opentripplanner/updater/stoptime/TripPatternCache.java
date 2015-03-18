package org.opentripplanner.updater.stoptime;

import java.util.HashMap;
import java.util.Map;

import org.onebusaway.gtfs.model.Route;
import org.opentripplanner.model.StopPattern;
import org.opentripplanner.routing.edgetype.TripPattern;
import org.opentripplanner.routing.graph.Graph;

/**
 * A synchronized cache of trip patterns that are added to the graph due to GTFS-realtime messages.
 */
public class TripPatternCache {

    private final Map<StopPattern, TripPattern> cache = new HashMap<>();
    
    /**
     * Get cached trip pattern or create one if it doesn't exist yet. If a trip pattern is created, vertices
     * and edges for this trip pattern are also created in the graph.
     * 
     * @param stopPattern stop pattern to retrieve/create trip pattern
     * @param route route of new trip pattern in case a new trip pattern will be created
     * @param graph graph to add vertices and edges in case a new trip pattern will be created
     * @return cached or newly created trip pattern
     */
    public synchronized TripPattern getOrCreateTripPattern(final StopPattern stopPattern,
            final Route route, final Graph graph) {
        // Check cache for trip pattern
        TripPattern tripPattern = cache.get(stopPattern);
        
        // Create TripPattern if it doesn't exist yet
        if (tripPattern == null) {
            tripPattern = new TripPattern(route, stopPattern);
            
            // Create vertices and edges for new TripPattern
            // TODO: purge these vertices and edges once in a while?
            tripPattern.makePatternVerticesAndEdges(graph, graph.index.stopVertexForStop);
            
            // TODO: Add pattern to graph index? 
            
            // Add pattern to cache
            cache.put(stopPattern, tripPattern);
        }
        
        return tripPattern;
    }

}
