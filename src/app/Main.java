package app;

import data_access.VehicleLocationDAO;
import data_access.VehicleLocationDataAccessInterface;
import entity.Route;
import entity.Vehicle;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        VehicleLocationDataAccessInterface dao = new VehicleLocationDAO();

//        ArrayList<Route> routes = dao.getRoutesByStop(1);
//        ArrayList<Integer> routes = ((VehicleLocationDAO) dao).getRouteList();
//        ((VehicleLocationDAO) dao).getStopTagsByRouteId(routes.get(0));

        ArrayList<Route> routesForStop = dao.getRoutesByStopTag("2371");

        for (Route route : routesForStop) {
            System.out.println("Route: " + route.getRouteId());
            for (Vehicle vehicle : route.getVehicles().values()) {
                System.out.println("Vehicle: " + vehicle.getId());
            }
        }


    }

}
