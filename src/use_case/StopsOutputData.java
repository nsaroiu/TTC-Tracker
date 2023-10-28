package use_case;

import entity.Location;
import entity.Stop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class StopsOutputData {

    private HashMap<String, Location> stopsMap;

    public StopsOutputData(HashMap<String, Location> stopsMap) {
        this.stopsMap = stopsMap;
    }

    public HashMap<String, Location> getStopsMap() {
        return stopsMap;
    }

}
