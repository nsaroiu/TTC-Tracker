package data_access;

import entity.Route;
import entity.Vehicle;

import java.util.ArrayList;

public interface VehicleLocationDataAccessInterface {

    public ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag);

    public ArrayList<Route> getRoutesByStopTag(String tag);

}
