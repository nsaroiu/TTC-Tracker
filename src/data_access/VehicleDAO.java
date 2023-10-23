package data_access;

import entity.Route;
import entity.Vehicle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.*;

public class VehicleDAO implements VehicleDataAccessInterface {

    public ArrayList<Vehicle> getVehiclesByRouteTag(String routeTag) {

        try {
            String[][] params = {{"command", "vehicleLocations"}, {"r", routeTag}, {"t", "0"}};
            Document doc = UmoiqApiCaller.getRequest(params);

            NodeList nodeList = doc.getElementsByTagName("vehicle");

            ArrayList<Vehicle> vehicles = new ArrayList<>();

            // For each vehicle in the nodeList, create a new Vehicle object and add it to the ArrayList
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                vehicles.add(new Vehicle(
                        Integer.parseInt(element.getAttribute("id")),
                        Integer.parseInt(element.getAttribute("speedKmHr")),
                        Float.parseFloat(element.getAttribute("lat")),
                        Float.parseFloat(element.getAttribute("lon")),
                        Integer.parseInt(element.getAttribute("heading")
                        )));
            }

            return vehicles;

        } catch (NullPointerException | InvalidRequestException e) {
                e.printStackTrace();
        }

        return null;

    }


}
