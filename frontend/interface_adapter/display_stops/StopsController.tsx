import {useCallback} from "react";
import {Stops} from "./StopsData";

type LatLngLiteral = google.maps.LatLngLiteral;
const StopsController = () => {
    const fetchData = async () => {
        try {
            const response = await fetch('http://localhost:8080/stops'); // Replace with your API endpoint
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            return await response.json();
            // Do something with the data, e.g., update state, show in UI, etc.
        } catch (error: any) {
            console.error('Error fetching data:', error.message);
            // Handle error, e.g., show an error message to the user
        }
    };

    const getStops = useCallback(async (): Promise<Stops|undefined> => {
        try {
            // Wait for the fetchData promise to resolve
            return await fetchData();
        } catch (error: any) {
            // Handle errors if the fetchData promise is rejected
            console.error('Error fetching data:', error.message);
        }
    }, []);

    return {
        getStops
    }
}

export default StopsController;