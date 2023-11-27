// StopsDetails.tsx
import React, { useState } from 'react';
import StopDetailsOutputData from "../interface_adapter/stop_details/StopDetailsData";

interface StopsDetailsProps {
    stopDetails: StopDetailsOutputData;
}

const StopsDetails: React.FC<StopsDetailsProps> = ({ stopDetails }) => {
    const [selectedRoute, setSelectedRoute] = useState<string | null>(null);

    const handleRouteChange = (routeTag: string) => {
        setSelectedRoute(routeTag);
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
                        <select onChange={(e) => handleRouteChange(e.target.value)} value={selectedRoute || ""}>
                            <option value="" disabled>Select a direction</option>
                            {Object.entries(stopDetails.routeTagsToDir[routeTag]).map(([dirTag, directionName]) => (
                                <option key={dirTag} value={dirTag}>
                                    {directionName}
                                </option>
                            ))}
                        </select>
                    </div>
                    {selectedRoute === routeTag && (
                        <div>
                            <h5>Selected Direction: {stopDetails.routeTagsToDir[routeTag][selectedRoute]}</h5>
                            {/* Additional content for the selected direction */}
                        </div>
                    )}
                </div>
            ))}
        </div>
    );
};

export default StopsDetails;
