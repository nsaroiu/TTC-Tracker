package com.example.backend.use_case.predictions.strategies;

import com.example.backend.entity.Location;
import com.example.backend.entity.Route;
import com.example.backend.entity.Vehicle;

import java.util.ArrayList;

public class StrategyInputDataBuilder {

    private StrategyInputData data;

    public StrategyInputDataBuilder() {
        data = new StrategyInputData();
    }

    public StrategyInputDataBuilder setDirTag(String dirTag) {
        data.setDirTag(dirTag);
        return this;
    }

    public StrategyInputDataBuilder setStopTag(String stopTag) {
        data.setStopTag(stopTag);
        return this;
    }

    public StrategyInputDataBuilder setRoute(Route route) {
        data.setRoute(route);
        return this;
    }

    public StrategyInputDataBuilder setVehicles(ArrayList<Vehicle> vehicles) {
        data.setVehicles(vehicles);
        return this;
    }

    public StrategyInputDataBuilder setShape(ArrayList<Location> shape) {
        data.setShape(shape);
        return this;
    }

    public StrategyInputDataBuilder setSchedule(ArrayList<String> schedule) {
        data.setSchedule(schedule);
        return this;
    }

    public StrategyInputData build() {
        return data;
    }

}
