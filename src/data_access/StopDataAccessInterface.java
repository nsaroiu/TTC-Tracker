package data_access;

import java.util.HashMap;
import java.util.HashSet;

public interface StopDataAccessInterface {

    public HashSet<String> getStopTagsByRouteTag(String routeTag);

    public HashMap<String, HashSet<String>> getRouteTagsToStopTags();

}
