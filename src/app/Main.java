package app;

import data_access.InvalidRequestException;
import data_access.UmoiqApiCaller;
import data_access.VehicleDAO;
import data_access.VehicleDataAccessInterface;

public class Main {

    public static void main(String[] args) throws InvalidRequestException {

        VehicleDataAccessInterface dao = new VehicleDAO();

        UmoiqApiCaller.getRequest(new String[][]{{"command", "routeConfig"}});


    }

}
