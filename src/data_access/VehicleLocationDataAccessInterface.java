package data_access;

import entity.Route;
import entity.Vehicle;

import java.util.ArrayList;

public interface VehicleLocationDataAccessInterface {

    public ArrayList<Vehicle> getVehiclesByRoute(int routeId);

    public ArrayList<Route> getRoutesByStopTag(String tag);

}
