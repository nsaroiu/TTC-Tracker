import { Marker, MarkerClusterer } from "@react-google-maps/api";
import React from "react";
import { DisplayStopsData } from "../interface_adapter/display_stops/StopsData";

type LatLngLiteral = google.maps.LatLngLiteral;

interface StopsProps {
    visibleStops: DisplayStopsData;
    mapZoom: number;
}

const Stops: React.FC<StopsProps> = ({ visibleStops, mapZoom }) => {
    return (
        <>
            {visibleStops && Object.keys(visibleStops).length > 0 && (
                mapZoom && mapZoom >= 17 ? (
                    // Display individual markers if zoom is greater than or equal to 16
                    Object.keys(visibleStops).map((key: string) => (
                        <Marker
                            key={key}
                            position={{ lat: visibleStops[key].lat, lng: visibleStops[key].lng }}
                            icon="https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png"
                        />
                    ))
                ) : (
                    // Use marker clustering if zoom is less than 16
                    <MarkerClusterer>
                        {(clusterer) =>
                            Object.keys(visibleStops).map((key, index) => (
                                <Marker
                                    key={index}
                                    position={{ lat: visibleStops[key].lat, lng: visibleStops[key].lng }}
                                    icon="https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png"
                                    clusterer={clusterer}
                                />
                            ))
                        }
                    </MarkerClusterer>
                )
            )}
        </>
    );
};

export default Stops;
