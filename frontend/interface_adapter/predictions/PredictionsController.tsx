import {PredictionsData} from "./PredictionsData";
import {useCallback} from "react";

const PredictionsController = () => {
    const fetchPredictions = async (routeTag: string, dirTag: string, stopTag: string):Promise<PredictionsData|undefined> => {
        try {
            const response = await fetch(`http://localhost:8080/predictions?routeTag=${routeTag}&dirTag=${dirTag}&stopTag=${stopTag}`, {
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

    const getPredictions = useCallback(async (routeTag: string, dirTag: string, stopTag: string): Promise<PredictionsData | undefined> => {
        try {
            // Wait for the fetchData promise to resolve
            const predictionsData = await fetchPredictions(routeTag, dirTag, stopTag);
            if (predictionsData) {
                return predictionsData;
            }

        } catch (error: any) {
            // Handle errors if the fetchData promise is rejected
            console.error('Error fetching data:', error.message);
            return undefined;
        }
    }, []);

    return {
        getPredictions
    }
}

export default PredictionsController;