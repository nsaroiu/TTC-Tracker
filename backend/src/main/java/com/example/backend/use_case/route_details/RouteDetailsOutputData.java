package com.example.backend.use_case.route_details;

import com.example.backend.entity.Location;

import java.util.ArrayList;

public class RouteDetailsOutputData {

    ArrayList<Location> shape;
    String routeTag;
    String dirTag;
    String dirName;

    public RouteDetailsOutputData(String routeTag, String dirTag, String dirName, ArrayList<Location> shape) {
        this.routeTag = routeTag;
        this.dirTag = dirTag;
        this.dirName = dirName;
        this.shape = shape;
    }

    public ArrayList<Location> getShape() {
        return shape;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public String getDirTag() {
        return dirTag;
    }

    public String getDirName() {
        return dirName;
    }
}
