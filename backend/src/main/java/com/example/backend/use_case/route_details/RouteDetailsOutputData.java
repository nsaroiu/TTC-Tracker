package com.example.backend.use_case.route_details;

import com.example.backend.entity.Location;

import java.util.ArrayList;

public class RouteDetailsOutputData {

    ArrayList<Location> shape;
    String dirName;

    public RouteDetailsOutputData(String dirName, ArrayList<Location> shape) {
        this.dirName = dirName;
        this.shape = shape;
    }

    public ArrayList<Location> getShape() {
        return shape;
    }

    public String getDirName() {
        return dirName;
    }
}
