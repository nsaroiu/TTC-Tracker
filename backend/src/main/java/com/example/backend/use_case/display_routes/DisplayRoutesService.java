package com.example.backend.use_case.display_routes;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DisplayRoutesService {
    @Autowired
    private RouteDataAccessInterface routeDataAccessObject;

    public HashMap<String, ArrayList<Location>> execute() {
        return routeDataAccessObject.getRouteShapes();
    }
}
