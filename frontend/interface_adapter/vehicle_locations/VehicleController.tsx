import {useCallback} from "react";
import {VehicleData} from "./VehicleData";
import LatLngLiteral = google.maps.LatLngLiteral;

const VehicleController = () => {
    const fetchData = async (routeTag: string): Promise<VehicleData | undefined> => {
        try {
            const response = await fetch(`http://localhost:8080/vehicle-locations?routeTag=${routeTag}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            if (!response.ok) {
                console.error(`HTTP error! Status: ${response.status}`);
                return undefined;
            }

            return await response.json();
        } catch (error: any) {
            console.error("Error fetching data:", error.message);
            return undefined;
        }
    }

    const getVehicleLocations = useCallback(async (routeTag: string): Promise<Array<LatLngLiteral> | undefined> => {
        try {
            const vehicleData = await fetchData(routeTag);
            if (vehicleData === undefined) {
                return undefined;
            }

           return vehicleData.vehicles.map((vehicle) => {
                return {
                    lat: vehicle.location.latitude,
                    lng: vehicle.location.longitude
                }
           });

        } catch (error: any) {
            console.error("Error fetching data:", error.message);
            return undefined;
        }
    }, []);


    return {
        getVehicleLocations
    }
}

export default VehicleController;