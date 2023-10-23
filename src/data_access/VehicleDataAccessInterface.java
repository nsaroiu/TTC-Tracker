package data_access;

import entity.Route;
import entity.Vehicle;

import java.util.ArrayList;

public interface VehicleDataAccessInterface {

    ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag);

}
