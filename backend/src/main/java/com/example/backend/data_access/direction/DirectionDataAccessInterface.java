package com.example.backend.data_access.direction;

import com.example.backend.entity.Location;
import com.example.backend.entity.RouteDirection;

import java.util.ArrayList;
import java.util.HashMap;

public interface DirectionDataAccessInterface {
    /** Returns a map of all directions that run on a route, given its route tag
     *
     * @return mapping of direction tag to RouteDirection for the route **/
    HashMap<String, RouteDirection> getDirectionsByRouteTag(String routeTag);

    /** Returns the shape of the direction, given its direction tag
     *
     * @return list of locations corresponding to the direction's path **/
    ArrayList<Location> getShapeByDirTag(String dirTag);

    /** Returns the average speed of vehicles serving the specified direction at the specified time
     * @param dirTag String representing the direction tag
     * @param hour String representing the time
     * @return float representing the average speed of vehicles serving the direction at the specified time **/
    float getAverageSpeed(String dirTag, String hour);
}
