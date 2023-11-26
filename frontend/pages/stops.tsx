import { Marker, MarkerClusterer } from "@react-google-maps/api";
import React from "react";
import { DisplayStopsData } from "../interface_adapter/display_stops/StopsData";

interface StopsProps {
    visibleStops: DisplayStopsData;
    mapZoom: number;
    updateSelectedStop: (stopTag: string) => void;
}

const Stops: React.FC<StopsProps> = ({ visibleStops, mapZoom, updateSelectedStop }) => {
    return (
        <>
            {visibleStops && visibleStops.length > 0 && (
                mapZoom && mapZoom >= 17 ? (
                    // Display individual markers if zoom is greater than or equal to 16
                    visibleStops.map((stop, index) => (
                        <Marker
                            key={stop.tag}
                            position={{ lat: stop.location.lat, lng: stop.location.lng }}
                            onClick={() => updateSelectedStop(stop.tag)}
                            icon="https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png"
                        />
                    ))
                ) : (
                    // Use marker clustering if zoom is less than 16
                    <MarkerClusterer>
                        {(clusterer) => (
                            visibleStops.map((stop, index) => (
                                <Marker
                                    key={stop.tag}
                                    position={{ lat: stop.location.lat, lng: stop.location.lng }}
                                    onClick={() => updateSelectedStop(stop.tag)}
                                    icon="https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png"
                                    clusterer={clusterer}
                                />
                            ))
                        )}
                    </MarkerClusterer>
                )
            )}
        </>
    );
};

export default Stops;
