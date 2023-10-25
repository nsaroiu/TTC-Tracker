package app;

import data_access.*;
import entity.Location;
import entity.Stop;

import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws InvalidRequestException {

        StopDataAccessInterface stopDAO = new StopDAO();

        HashMap<String, Location> stops = stopDAO.getAllStopTagsAndLocations();


    }

}
