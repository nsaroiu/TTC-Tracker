package com.example.backend.use_case.vehicle_locations;

import com.example.backend.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VehicleLocationsImplementation implements VehicleLocationsService {
    @Autowired
    private VehicleLocationsDataAccessInterface vehicleLocationsDAO;

    public VehicleLocationsOutputData execute(String routeTag, String dirTag) {
        ArrayList<Vehicle> routeVehicles = vehicleLocationsDAO.getVehiclesByRouteTag(routeTag);
        ArrayList<Vehicle> dirVehicles = new ArrayList<>();

        for (Vehicle vehicle : routeVehicles) {
            if (vehicle.getDirectionTag().equals(dirTag)) {
                dirVehicles.add(vehicle);
            }
        }

        return new VehicleLocationsOutputData(dirVehicles);
    }
}
