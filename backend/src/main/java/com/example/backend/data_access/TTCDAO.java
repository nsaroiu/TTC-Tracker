package com.example.backend.data_access;

import com.example.backend.data_access.direction.DirectionDataAccessInterface;
import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.data_access.vehicle.VehicleDataAccessInterface;
import com.example.backend.entity.Location;
import com.example.backend.entity.Route;
import com.example.backend.entity.Stop;
import com.example.backend.entity.Vehicle;
import com.example.backend.use_case.display_stops.StopsDataAccessInterface;
import com.example.backend.use_case.predictions.PredictionsDataAccessInterface;
import com.example.backend.use_case.route_details.RouteDetailsDataAccessInterface;
import com.example.backend.use_case.stop_details.StopDetailsDataAccessInterface;
import com.example.backend.use_case.vehicle_locations.VehicleLocationsDataAccessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class TTCDAO implements StopsDataAccessInterface, PredictionsDataAccessInterface, RouteDetailsDataAccessInterface, StopDetailsDataAccessInterface, VehicleLocationsDataAccessInterface {

    @Autowired
    private StopDataAccessInterface stopDAO;

    @Autowired
    private DirectionDataAccessInterface directionDAO;

    @Autowired
    private VehicleDataAccessInterface vehicleDAO;

    @Autowired
    private RouteDataAccessInterface routeDAO;

    /**
     * Returns a set of all stops in the TTC.
     *
     * @return HashSet of Stop Objects.
     */
    public HashSet<Stop> getAllStops() {
        return stopDAO.getAllStops();
    }

    /**
     * Given a stop tag, return a set of route tags that pass through the stop.
     *
     * @param stopTag String representing stop tag
     * @return HashSet of route tags that pass through the stop
     */
    public HashSet<String> getRouteTagsByStopTag(String stopTag) {
        return routeDAO.getRouteTagsByStopTag(stopTag);
    }

    /**
     * Returns the shape of the direction, given its direction tag
     *
     * @param dirTag String representing the direction tag
     * @return list of locations corresponding to the direction's path
     **/
    public ArrayList<Location> getShapeByDirTag(String dirTag) {
        return directionDAO.getShapeByDirTag(dirTag);
    }

    /**
     * Returns the average speed of vehicles serving the specified direction at the specified time
     *
     * @param dirTag String representing the direction tag
     * @param hour   String representing the time
     * @return float representing the average speed of vehicles serving the direction at the specified time
     **/
    public float getAverageSpeed(String dirTag, String hour) {
        return directionDAO.getAverageSpeed(dirTag, hour);
    }

    /**
     * Returns a Route object for the given route tag.
     *
     * @param routeTag String representing route tag
     * @return Route object for the given route tag
     * @see Route
     */
    public Route getRouteByRouteTag(String routeTag) {
        return routeDAO.getRouteByRouteTag(routeTag);
    }

    /**
     * Returns a list of vehicles for a given route.
     *
     * @param routeTag String representing route tag
     * @return ArrayList of Vehicle Objects that pertain to the route
     */
    public ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag) {
        return vehicleDAO.getVehiclesByRouteTag(routeTag);
    }

    /**
     * Returns a list of scheduled arrival times of vehicles serving the specified direction at the specified stop.
     *
     * @param stopTag String representing the stop tag
     * @param dirTag  String representing the direction tag
     * @return ArrayList of Strings representing the scheduled arrival times
     */
    public ArrayList<String> getScheduledArrivals(String stopTag, String dirTag) {
        return stopDAO.getScheduledArrivals(stopTag, dirTag);
    }
}
