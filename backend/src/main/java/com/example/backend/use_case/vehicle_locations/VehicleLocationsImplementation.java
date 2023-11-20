package com.example.backend.use_case.vehicle_locations;

import com.example.backend.data_access.vehicle.VehicleDataAccessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleLocationsImplementation implements VehicleLocationsService {
    @Autowired
    VehicleDataAccessInterface vehicleDAO;

    public VehicleLocationsOutputData execute(String routeTag) {
        return new VehicleLocationsOutputData(vehicleDAO.getVehiclesByRouteTag(routeTag));
    }
}
