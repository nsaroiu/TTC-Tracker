package com.example.backend.use_case.route_details;

import com.example.backend.entity.Location;
import com.example.backend.entity.Route;

import java.util.ArrayList;

public interface RouteDetailsDataAccessInterface {

    /** Returns a Route object for the given route tag.
     *
     * @return Route object for the given route tag
     * @see Route
     */
    Route getRouteByRouteTag(String routeTag);

    /** Returns the shape of the direction, given its direction tag
     *
     * @return list of locations corresponding to the direction's path **/
    ArrayList<Location> getShapeByDirTag(String dirTag);
}
