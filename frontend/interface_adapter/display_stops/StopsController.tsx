import { useCallback } from "react";
import { StopsData, DisplayStopsData, StopObject } from "./StopsData";

const StopsController = () => {
    const fetchData = async (): Promise<StopsData | undefined> => {
        try {
            const response = await fetch("http://localhost:8080/stops");
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            return await response.json();
        } catch (error: any) {
            console.error("Error fetching data:", error.message);
        }
    };

    const getStops = useCallback(async (): Promise<DisplayStopsData | undefined> => {
        try {
            const data: StopsData | undefined = await fetchData();
            if (!data) {
                return undefined;
            }

            const stops: DisplayStopsData = data.stops.map((stop: StopObject) => ({
                name: stop.name,
                tag: stop.tag,
                location: {
                    lat: stop.location.latitude,
                    lng: stop.location.longitude,
                },
            }));

            return stops;
        } catch (error: any) {
            console.error("Error fetching data:", error.message);
            return undefined;
        }
    }, [fetchData]);

    return {
        getStops,
    };
};

export default StopsController;
