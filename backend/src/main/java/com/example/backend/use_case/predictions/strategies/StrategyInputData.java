package com.example.backend.use_case.predictions.strategies;

import com.example.backend.entity.Location;
import com.example.backend.entity.Route;
import com.example.backend.entity.Vehicle;

import java.util.ArrayList;

public class StrategyInputData {

    private String dirTag;
    private String stopTag;
    private Route route;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Location> shape;
    private ArrayList<String> schedule;
    private float avgSpeed;

    public StrategyInputData() {}

    public String getDirTag() {
        return dirTag;
    }

    public String getStopTag() {
        return stopTag;
    }

    public Route getRoute() {
        return route;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<Location> getShape() {
        return shape;
    }

    public ArrayList<String> getSchedule() {
        return schedule;
    }

    public float getAvgSpeed() { return avgSpeed; }

    public void setDirTag(String dirTag) {
        this.dirTag = dirTag;
    }

    public void setStopTag(String stopTag) {
        this.stopTag = stopTag;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setShape(ArrayList<Location> shape) {
        this.shape = shape;
    }

    public void setSchedule(ArrayList<String> schedule) {
        this.schedule = schedule;
    }

    public void setAvgSpeed(float avgSpeed) { this.avgSpeed = avgSpeed; }

}
