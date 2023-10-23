package app;

import data_access.InvalidRequestException;
import data_access.UmoiqApiCaller;
import data_access.VehicleDAO;
import data_access.VehicleDataAccessInterface;
import entity.Vehicle;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InvalidRequestException {

        VehicleDataAccessInterface dao = new VehicleDAO();

//        UmoiqApiCaller.getRequest(new String[][]{{"command", "routeConfig"}});
        ArrayList<Vehicle> vehicles = dao.getVehiclesByRouteTag("510");
        System.out.println(vehicles.get(0).getLocation().getDistance(vehicles.get(1).getLocation()));


    }

}
