package data_access;

import entity.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface RouteDataAccessInterface {

    public ArrayList<String> getRouteTagList();

    public ArrayList<Route> getRoutesByStopTag(String tag);

    public HashMap<String, HashSet<String>> getStopTagsToRouteTags();

}
