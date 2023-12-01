import React from 'react';
import LatLngLiteral = google.maps.LatLngLiteral;
import { Marker } from "@react-google-maps/api";
interface VehicleLocationsProps {
    vehicleLocations: Array<LatLngLiteral>;
}

const VehicleLocations: React.FC<VehicleLocationsProps> = ({vehicleLocations}) => {
    return (
        <>
            {vehicleLocations.map((vehicleLocation, index) => (
                <Marker
                    key={index}
                    position={vehicleLocation}
                    icon={'data:image/png;base64,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'}
                />
            ))}
        </>
    );
}

export default VehicleLocations;