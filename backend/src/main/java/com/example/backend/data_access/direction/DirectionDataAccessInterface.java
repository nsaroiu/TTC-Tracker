package com.example.backend.data_access.direction;

import com.example.backend.entity.Location;
import com.example.backend.entity.RouteDirection;

import java.util.ArrayList;
import java.util.HashMap;

public interface DirectionDataAccessInterface {
    HashMap<String, RouteDirection> getDirectionsByRouteTag(String routeTag);

    ArrayList<Location> getShapeByDirTag(String dirTag);
}
