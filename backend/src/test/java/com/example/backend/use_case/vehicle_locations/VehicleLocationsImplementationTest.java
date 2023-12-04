package com.example.backend.use_case.vehicle_locations;

import com.example.backend.data_access.vehicle.VehicleDataAccessInterface;
import com.example.backend.entity.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class VehicleLocationsImplementationTest {

    @Mock
    private VehicleDataAccessInterface vehicleDAO;

    @InjectMocks
    private VehicleLocationsImplementation vehicleLocationsImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Vehicle mockVehicle = new Vehicle(0, 100, 0.0f, 0.0f, 0, "dirTag");

        ArrayList<Vehicle> mockVehicles = new ArrayList<>();

        mockVehicles.add(mockVehicle);

        when(vehicleDAO.getVehiclesByRouteTag("nonemptyVehicles")).thenReturn(mockVehicles);
        when(vehicleDAO.getVehiclesByRouteTag("emptyVehicles")).thenReturn(new ArrayList<>());
    }

    @Test
    void execute_ReturnsVehicleLocationsOutputData_WhenVehiclesExist() {
        // Arrange
        String routeTag = "nonemptyVehicles";
        String dirTag = "dirTag";

        // Act
        VehicleLocationsOutputData result = vehicleLocationsImplementation.execute(routeTag, dirTag);

        // Assert
        assert(!result.getVehicles().isEmpty());
    }

    @Test
    void execute_ReturnsVehicleLocationsOutputData_WhenNoDirTagVehiclesExist() {
        // Arrange
        String routeTag = "nonemptyVehicles";
        String dirTag = "wrongDirTag";

        // Act
        VehicleLocationsOutputData result = vehicleLocationsImplementation.execute(routeTag, dirTag);

        // Assert
        assert(result.getVehicles().isEmpty());
    }

    @Test
    void execute_ReturnsEmptyVehicleLocationsOutputData_WhenNoVehiclesExist() {
        // Arrange
        String routeTag = "emptyVehicles";
        String dirTag = "dirTag";

        when(vehicleDAO.getVehiclesByRouteTag(anyString())).thenReturn(new ArrayList<>());

        // Act
        VehicleLocationsOutputData result = vehicleLocationsImplementation.execute(routeTag, dirTag);

        // Assert
        assert(result.getVehicles().isEmpty());
    }

    @Test
    void execute_ReturnsEmptyVehicleLocationsOutputData_WhenRouteDoesntExist() {
        // Arrange
        String routeTag = "wrongRouteTag";
        String dirTag = "dirTag";

        when(vehicleDAO.getVehiclesByRouteTag(anyString())).thenReturn(new ArrayList<>());

        // Act
        VehicleLocationsOutputData result = vehicleLocationsImplementation.execute(routeTag, dirTag);

        // Assert
        assert(result.getVehicles().isEmpty());
    }

}
