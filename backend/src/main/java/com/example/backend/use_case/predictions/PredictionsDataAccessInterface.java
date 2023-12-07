package com.example.backend.use_case.predictions;

import com.example.backend.entity.Location;
import com.example.backend.entity.Route;
import com.example.backend.entity.Vehicle;

import java.util.ArrayList;

public interface PredictionsDataAccessInterface {

    /** Returns the shape of the direction, given its direction tag
     *
     * @return list of locations corresponding to the direction's path **/
    ArrayList<Location> getShapeByDirTag(String dirTag);

    /** Returns the average speed of vehicles serving the specified direction at the specified time
     * @param dirTag String representing the direction tag
     * @param hour String representing the time
     * @return float representing the average speed of vehicles serving the direction at the specified time **/
    float getAverageSpeed(String dirTag, String hour);

    /** Returns a Route object for the given route tag.
     *
     * @return Route object for the given route tag
     * @see Route
     */
    Route getRouteByRouteTag(String routeTag);

    /** Returns a list of vehicles for a given route.
     *
     * @param routeTag String representing route tag
     * @return ArrayList of Vehicle Objects that pertain to the route
     */
    ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag);

    /** Returns a list of scheduled arrival times of vehicles serving the specified direction at the specified stop.
     *
     * @param stopTag String representing the stop tag
     * @param dirTag  String representing the direction tag
     * @return ArrayList of Strings representing the scheduled arrival times
     */
    ArrayList<String> getScheduledArrivals(String stopTag, String dirTag);
}
