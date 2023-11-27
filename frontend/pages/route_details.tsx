import React from 'react';
import {RouteData} from "../interface_adapter/route_details/RouteDetailsData";
import { Polyline } from "@react-google-maps/api";

interface RouteDetailsProps {
    routeDetails: RouteData;
}

const RouteDetails: React.FC<RouteDetailsProps> = ({routeDetails }) => {
    return (
        <>
            <Polyline
                path={routeDetails.shape}
                options={{
                    strokeColor: "#FF0000",
                    strokeOpacity: 1.0,
                    strokeWeight: 5,
                }}
            />
            <p>Route Name: {routeDetails.dirName}</p>
        </>
    );

}

export default RouteDetails;