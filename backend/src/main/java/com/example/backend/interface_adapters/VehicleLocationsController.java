package com.example.backend.interface_adapters;

import com.example.backend.use_case.vehicle_locations.VehicleLocationsOutputData;
import com.example.backend.use_case.vehicle_locations.VehicleLocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class VehicleLocationsController {
    @Autowired
    private VehicleLocationsService vehicleLocationsImplementation;

    @PostMapping("/vehicle-locations")
    public VehicleLocationsOutputData execute(@RequestParam String routeTag, @RequestParam String dirTag) {
        return vehicleLocationsImplementation.execute(routeTag, dirTag);
    }

}
