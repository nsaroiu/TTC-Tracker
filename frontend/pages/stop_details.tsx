// StopsDetails.tsx
import React from 'react';
import StopDetailsOutputData from "../interface_adapter/stop_details/StopDetailsData";

interface StopsDetailsProps {
    stopDetails: StopDetailsOutputData; // Import StopDetailsOutputData interface
}

const StopsDetails: React.FC<StopsDetailsProps> = ({ stopDetails }) => {
    // Implement your UI to display stopDetails in an info window
    return (
        <div>
            <h3>{stopDetails.stopName}</h3>
    {/* Display additional details as needed */}
    </div>
);
};

export default StopsDetails;
