package com.example.backend.use_case.predictions;

import com.example.backend.data_access.route.RouteDataAccessInterface;
import com.example.backend.data_access.stop.StopDataAccessInterface;
import com.example.backend.data_access.vehicle.VehicleDataAccessInterface;
import com.example.backend.data_access.direction.DirectionDataAccessInterface;
import com.example.backend.entity.Location;
import com.example.backend.entity.Route;
import com.example.backend.entity.Vehicle;
import com.example.backend.use_case.predictions.strategies.CalculateDistanceStrategy;
import com.example.backend.use_case.predictions.strategies.ScheduleStrategy;
import com.example.backend.use_case.predictions.strategies.Strategy;
import com.example.backend.use_case.predictions.strategies.StrategyInputDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

@Service
public class PredictionsImplementation implements PredictionsService {
    @Autowired
    private RouteDataAccessInterface routeDAO;
    @Autowired
    private VehicleDataAccessInterface vehicleDAO;
    @Autowired
    private StopDataAccessInterface stopDAO;
    @Autowired
    private DirectionDataAccessInterface directionDAO;
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }


    public PredictionsOutputData execute(String routeTag, String dirTag, String stopTag) {
        Route route = routeDAO.getRouteByRouteTag(routeTag);
        ArrayList<Vehicle> vehicles = vehicleDAO.getVehiclesByRouteTag(routeTag);
        ArrayList<Location> shape = directionDAO.getShapeByDirTag(dirTag);
        setStrategy(new CalculateDistanceStrategy());
        StrategyInputDataBuilder builder = new StrategyInputDataBuilder();
        builder = builder.setDirTag(dirTag)
                .setStopTag(stopTag)
                .setRoute(route)
                .setVehicles(vehicles)
                .setShape(shape)
                .setAvgSpeed(directionDAO.getAverageSpeed(dirTag, String.valueOf(LocalDateTime.now(ZoneOffset.UTC).getHour())));
        ArrayList<Float> predictions = strategy.execute(builder.build());
        if (predictions.isEmpty()) {
            setStrategy(new ScheduleStrategy());
            builder = builder.setSchedule(stopDAO.getScheduledArrivals(stopTag, dirTag));
            predictions = strategy.execute(builder.build());
        }
        return new PredictionsOutputData(predictions);
    }
}
