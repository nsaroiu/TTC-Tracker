import {RouteDetailsOutputData, RouteData, RouteParam} from "./RouteDetailsData";
import {useCallback} from "react";

const RouteDetailsController = () => {
    const fetchRouteDetails = async (routeTag: string, dirTag: string):Promise<RouteDetailsOutputData|undefined> => {
        try {
            const response = await fetch(`http://localhost:8080/route-details?routeTag=${routeTag}&dirTag=${dirTag}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            return await response.json();
            // Do something with the data, e.g., update state, show in UI, etc.
        } catch (error: any) {
            console.error('Error fetching data:', error.message);
            // Handle error, e.g., show an error message to the user
        }
    }

    const getRouteDetails = useCallback(async (routeParam: RouteParam): Promise<RouteData | undefined> => {
        try {
            // Wait for the fetchData promise to resolve
            const routedata = await fetchRouteDetails(routeParam.routeTag, routeParam.dirTag);
            if (routedata) {
                return {
                    shape: routedata.shape.map((location) => ({ lat: location.latitude, lng: location.longitude })),
                    dirName: routedata.dirName
                };
            }

        } catch (error: any) {
            // Handle errors if the fetchData promise is rejected
            console.error('Error fetching data:', error.message);
            return undefined;
        }
    }, []);

    return {
        getRouteDetails
    }
}

export default RouteDetailsController;