package use_case.vehicle_locations;

public interface VehicleLocationsOutputBoundary {
    void prepareSuccessView(VehicleLocationsOutputData data);

    void prepareFailView(String error);
}
