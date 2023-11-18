import { Marker, MarkerClusterer } from "@react-google-maps/api";
import React from "react";

type LatLngLiteral = google.maps.LatLngLiteral;

interface StopsProps {
    visibleStops: Array<LatLngLiteral>;
    mapZoom: number;
}

const Stops: React.FC<StopsProps> = ({ visibleStops, mapZoom }) => {
    return (
        <>
            {visibleStops.length > 0 && (
                mapZoom && mapZoom >= 17 ? (
                    // Display individual markers if zoom is greater than or equal to 16
                    visibleStops.map((stop, index) => (
                        <Marker
                            key={index}
                            position={stop}
                            icon="https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png"
                        />
                    ))
                ) : (
                    // Use marker clustering if zoom is less than 16
                    <MarkerClusterer>
                        {(clusterer) =>
                            visibleStops.map((stop, index) => (
                                <Marker
                                    key={index}
                                    position={stop}
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
