package interface_adapters.vehicle_locations;

import use_case.vehicle_locations.VehicleLocationsInputBoundary;
import use_case.vehicle_locations.VehicleLocationsInputData;

public class VehicleLocationsController {
    final VehicleLocationsInputBoundary vehicleLocationsUseCaseInteractor;
    public VehicleLocationsController(VehicleLocationsInputBoundary vehicleLocationsUseCaseInteractor) {
        this.vehicleLocationsUseCaseInteractor = vehicleLocationsUseCaseInteractor;
    }

    public void execute() {
        vehicleLocationsUseCaseInteractor.execute(new VehicleLocationsInputData());
    }
}
