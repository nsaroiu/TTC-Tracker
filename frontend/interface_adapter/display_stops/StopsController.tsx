import {useCallback} from "react";
import {StopsData, DisplayStopsData} from "./StopsData";

const StopsController = () => {
    const fetchData = async (): Promise<StopsData|undefined> => {
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

    const getStops = useCallback(async (): Promise<DisplayStopsData | undefined> => {
        try {
            // Wait for the fetchData promise to resolve
            const data: StopsData | undefined = await fetchData();
            if (!data) {
                return undefined;
            }

            const stops: DisplayStopsData = {};
            Object.keys(data).forEach((key: string) => {
                stops[key] = {
                    lat: data[key].latitude,
                    lng: data[key].longitude,
                };
            });

            return stops;
        } catch (error: any) {
            // Handle errors if the fetchData promise is rejected
            console.error('Error fetching data:', error.message);
            return undefined; // Make sure to return something in case of an error
        }
    }, []);



    return {
        getStops
    }
}

export default StopsController;