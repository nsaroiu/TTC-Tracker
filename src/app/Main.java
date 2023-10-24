package app;

import data_access.*;
import entity.Stop;
import entity.Vehicle;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws InvalidRequestException {

        StopDataAccessInterface stopDAO = new StopDAO();

        HashSet<Stop> stops = stopDAO.getAllStops();

        for (Stop stop : stops) {
            System.out.println(stop.getTag());
        }

    }

}
