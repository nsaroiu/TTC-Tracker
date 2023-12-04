package com.example.backend.use_case.route_details;

import com.example.backend.data_access.direction.DirectionDataAccessInterface;
import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.entity.Route;
import com.example.backend.entity.RouteDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteDetailsImplementation implements RouteDetailsService {
    @Autowired
    private RouteDataAccessInterface routeDAO;
    @Autowired
    private DirectionDataAccessInterface directionDAO;

    /** Returns the route details for the given routeTag and dirTag. Returns null if the routeTag or dirTag is invalid.
     *
     * @param routeTag String representing the routeTag
     * @param dirTag String representing the dirTag
     * @return RouteDetailsOutputData object containing the routeTag, dirTag, dirName, and routeShape. Null if the routeTag or dirTag is invalid.
     */
    public RouteDetailsOutputData execute(String routeTag, String dirTag) {
        Route route = routeDAO.getRouteByRouteTag(routeTag);

        if (route == null) {
            return null;
        }

        RouteDirection routeDirection = route.getRouteDirections().get(dirTag);

        if (routeDirection == null) {
            return null;
        }

        return new RouteDetailsOutputData(
                routeDirection.getName(),
                directionDAO.getShapeByDirTag(dirTag)
        );
    }

}
