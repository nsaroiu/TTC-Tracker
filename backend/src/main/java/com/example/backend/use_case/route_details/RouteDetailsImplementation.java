package com.example.backend.use_case.route_details;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteDetailsImplementation implements RouteDetailsService {
    @Autowired
    private RouteDataAccessInterface routeDAO;

    /** Returns the route details for the given routeTag and dirTag. Returns null if the routeTag or dirTag is invalid.
     *
     * @param routeTag String representing the routeTag
     * @param dirTag String representing the dirTag
     * @return RouteDetailsOutputData object containing the routeTag, dirTag, dirName, and routeShape. Null if the routeTag or dirTag is invalid.
     */
    public RouteDetailsOutputData execute(String routeTag, String dirTag) {
        Route route = routeDAO.getRouteByRouteTag(routeTag);

        // TODO: Fix error handling
        if (route == null) {
            return null;
        }

        String dirName = route.getRouteDirections().get(dirTag).getName();

        if (dirName == null) {
            return null;
        }

        return new RouteDetailsOutputData(
                routeTag,
                dirTag,
                dirName,
                routeDAO.getRouteShapes().get(routeTag)
        );
    }

}
