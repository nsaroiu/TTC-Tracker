package data_access;

import entity.Route;
import entity.Vehicle;

import java.util.ArrayList;

public interface VehicleDataAccessInterface {

    public ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag);

}
