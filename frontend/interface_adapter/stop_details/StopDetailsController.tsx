import StopDetailsOutputData from "./StopDetailsData";
import {useCallback} from "react";
const StopDetailsController = () => {
    const fetchStopDetails = async (stopTag: string):Promise<StopDetailsOutputData|undefined> => {
        try {
            const response = await fetch(`http://localhost:8080/stop-details?stopTag=${stopTag}`, {
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
    };

    const getStopDetails = useCallback(async (stopTag: string): Promise<StopDetailsOutputData | undefined> => {
        try {
            // Wait for the fetchData promise to resolve
            return await fetchStopDetails(stopTag);
        } catch (error: any) {
            // Handle errors if the fetchData promise is rejected
            console.error('Error fetching data:', error.message);
            return undefined;
        }
    }, []);

    return {
        getStopDetails
    }
};

export default StopDetailsController;
