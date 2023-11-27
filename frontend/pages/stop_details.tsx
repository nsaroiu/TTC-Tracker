import React, { useState } from 'react';
import StopDetailsOutputData from "../interface_adapter/stop_details/StopDetailsData";
import { RouteParam } from "../interface_adapter/route_details/RouteDetailsData";

interface StopsDetailsProps {
    stopDetails: StopDetailsOutputData;
    UpdateSelectedRoute: (routeParam: RouteParam) => void;
}

const StopsDetails: React.FC<StopsDetailsProps> = ({ stopDetails, UpdateSelectedRoute }) => {
    const [selectedRoute, setSelectedRoute] = useState<string | null>(null);
    const [selectedDirection, setSelectedDirection] = useState<string | null>(null);
    const [dirTag, setDirTag] = useState<string | null>(null);

    const handleRouteChange = (routeTag: string, dirTag: string, dirName: string) => {
        setSelectedRoute(routeTag);
        setSelectedDirection(dirName);
        UpdateSelectedRoute({ routeTag, dirTag });
    };

    // Implement your UI to display stopDetails in an info window
    return (
        <div>
            <h3>{stopDetails.stopName}</h3>
            {Object.keys(stopDetails.routeTagsToDir).map((routeTag) => (
                <div key={routeTag}>
                    <h4>{routeTag}</h4>
                    <div>
                        <label>Select Direction:</label>
                        <select
                            onChange={(e) => {
                                const dirTag:string = e.target.value;
                                const dirName = stopDetails.routeTagsToDir[routeTag][dirTag];
                                setDirTag(dirTag); // Update dirTag directly
                                setSelectedDirection(dirName); // Update selectedDirection directly
                                handleRouteChange(routeTag, dirTag, dirName);
                            }}
                            value={dirTag || ""}
                        >
                            <option value="" disabled>Select a direction</option>
                            {Object.entries(stopDetails.routeTagsToDir[routeTag]).map(([dirTag, directionName]) => (
                                <option key={dirTag} value={dirTag}>
                                    {directionName}
                                </option>
                            ))}
                        </select>
                    </div>
                </div>
            ))}
            {stopDetails && selectedRoute && selectedDirection && (
                <div>
                    <h5>Selected Direction: {selectedDirection}</h5>
                </div>
            )}
        </div>
    );
};

export default StopsDetails;
