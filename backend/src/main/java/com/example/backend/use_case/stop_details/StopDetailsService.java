package com.example.backend.use_case.stop_details;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.entity.Location;
import com.example.backend.entity.Route;
import com.example.backend.entity.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class StopDetailsService {
@Autowired
    private RouteDataAccessInterface routeDataAccessObject;
    //private StopDataAccessInterface stopDataAccessObject;

//    public StopDetailsService(StopDataAccessInterface stopDataAccessObject, RouteDataAccessInterface routeDataAccessObject){
//        this.stopDataAccessObject = stopDataAccessObject;
//        this.routeDataAccessObject = routeDataAccessObject;
//    }

    public StopDetailsOutputData execute(StopDetailsInputData inputData) throws Exception {
        //Extract the stop tag
        String stopTag = inputData.getStopTag();
        //TODO: Replace this part with the actual name once names for Stops are implemented.
        //TODO: Introduce a way to get one stop by all stops while doing that
        String stopName = stopTag;
        //Use the stop tag to get the tags for all its routes
        HashSet<String> routeTags = routeDataAccessObject.getRouteTagsByStopTag(stopTag);
        //Get a mapping of routeTags to shapes
        HashMap<String, ArrayList<Location>> routeToShapes = routeDataAccessObject.getRouteShapes();
        //Start accumulating the Hashmap
        //Wrapper contains the shape and routeTag
        HashMap<Route,ArrayList<Object>> routeToWrapper = new HashMap<>();
        for (String routeTag : routeTags){
            //Get each route by routeTag
            Route route = routeDataAccessObject.getRouteByRouteTag(routeTag);
            //Get each shape by routeTag
            ArrayList<Location> shape = routeToShapes.get(routeTag);
            //Wrap the shape and the routeTag
            ArrayList<Object> wrapper = new ArrayList();
            wrapper.add(shape);
            wrapper.add(routeTag);
            //Add mapping from Route to wrapper (shape and routeTag)
            routeToWrapper.put(route,wrapper);
        }

        //Initialize new outputData with the stopName and the route
        StopDetailsOutputData outputData = new StopDetailsOutputData(stopName, routeToWrapper);
        //Return the outputData
        return outputData;
    }
}
