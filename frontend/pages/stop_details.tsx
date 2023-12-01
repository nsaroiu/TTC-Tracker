import React, {useEffect, useState} from 'react';
import StopDetailsOutputData from "../interface_adapter/stop_details/StopDetailsData";
import { RouteParam } from "../interface_adapter/route_details/RouteDetailsData";

interface StopsDetailsProps {
    stopDetails: StopDetailsOutputData;
    UpdateSelectedRoute: (routeParam: RouteParam) => void;
}

const StopsDetails: React.FC<StopsDetailsProps> = ({ stopDetails, UpdateSelectedRoute }) => {
    const [selectedRoute, setSelectedRoute] = useState<string | null>(null);
    const [selectedDirection, setSelectedDirection] = useState<string | null>(null);
    const [selectedRouteTag, setSelectedRouteTag] = useState<string | null>(null);

    const handleRouteChange = (routeTag: string, dirTag: string, dirName: string) => {
        setSelectedRoute(routeTag);
        setSelectedDirection(dirName);
        UpdateSelectedRoute({ routeTag, dirTag });
    };

    useEffect(() => {
        setSelectedRouteTag(null);
        setSelectedRoute(null);
        setSelectedDirection(null);
    }, [stopDetails]);

    // Implement your UI to display stopDetails in an info window
    return (
        <div>
            <h3>{stopDetails.stopName}</h3>
            <div>
                <label>Select Route Tag:</label>
                <select
                    onChange={(e) => {
                        const routeTag = e.target.value;
                        setSelectedRouteTag(routeTag);
                        setSelectedRoute(null);
                        setSelectedDirection(null);
                    }}
                    value={selectedRouteTag || ""}
                >
                    <option value="" disabled>Select a route tag</option>
                    {Object.keys(stopDetails.routeTagsToDir).map((routeTag) => (
                        <option key={routeTag} value={routeTag}>
                            {routeTag}
                        </option>
                    ))}
                </select>
            </div>
            {selectedRouteTag && stopDetails.routeTagsToDir[selectedRouteTag] && (
                <div>
                    <label>Select Direction:</label>
                    <select
                        onChange={(e) => {
                            const dirTag: string = e.target.value;
                            const dirName = stopDetails.routeTagsToDir[selectedRouteTag][dirTag];
                            setSelectedRoute(selectedRouteTag);
                            setSelectedDirection(dirName);
                            handleRouteChange(selectedRouteTag, dirTag, dirName);
                        }}
                        value={""}
                    >
                        <option value="" disabled>Select a direction</option>
                        {Object.entries(stopDetails.routeTagsToDir[selectedRouteTag]).map(([dirTag, directionName]) => (
                            <option key={dirTag} value={dirTag}>
                                {directionName}
                            </option>
                        ))}
                    </select>
                </div>
            )}
            {stopDetails && selectedRoute && selectedDirection && (
                <div>
                    <h5>Selected Direction: {selectedDirection}</h5>
                </div>
            )}
        </div>
    );
};

export default StopsDetails;
