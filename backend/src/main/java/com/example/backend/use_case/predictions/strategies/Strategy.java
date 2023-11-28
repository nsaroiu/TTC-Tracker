package com.example.backend.use_case.predictions.strategies;

import com.example.backend.data_access.direction.DirectionDataAccessInterface;
import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.data_access.vehicle.VehicleDataAccessInterface;
import com.example.backend.entity.Location;
import com.example.backend.entity.Route;
import com.example.backend.entity.Vehicle;

import java.util.ArrayList;

public interface Strategy {

    public ArrayList<Float> execute(Route route, ArrayList<Vehicle> vehicles, ArrayList<Location> shape, String dirTag, String stopTag);
}
