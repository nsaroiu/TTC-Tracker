import { Marker, MarkerClusterer, InfoWindow } from "@react-google-maps/api";
import React, { useState } from "react";
import {DisplayStopsData, DisplayStopsObject} from "../interface_adapter/display_stops/StopsData";
import LatLngLiteral = google.maps.LatLngLiteral;

interface StopsProps {
    visibleStops: DisplayStopsData;
    mapZoom: number;
    updateSelectedStop: (stopTag: string, location: LatLngLiteral) => void;
}

const Stops: React.FC<StopsProps> = ({ visibleStops, mapZoom, updateSelectedStop }) => {
    const [hoveredStop, setHoveredStop] = useState<string | null>(null);

    const handleMarkerHover = (stopTag: string) => {
        setHoveredStop(stopTag);
    };

    const handleMarkerMouseOut = () => {
        setHoveredStop(null);
    };

    return (
        <>
            {visibleStops && visibleStops.length > 0 && (
                <>
                    {mapZoom <= 16 ? (
                        <MarkerClusterer>
                            {(clusterer) => (
                                <>
                                    {visibleStops.map((stop: DisplayStopsObject) => (
                                        <Marker
                                            key={stop.tag}
                                            position={{ lat: stop.location.lat, lng: stop.location.lng }}
                                            onMouseOver={() => handleMarkerHover(stop.tag)}
                                            onMouseOut={handleMarkerMouseOut}
                                            onClick={() => updateSelectedStop(stop.tag, stop.location)}
                                            clusterer={clusterer}
                                        >
                                            {hoveredStop === stop.tag && (
                                                <InfoWindow position={{ lat: stop.location.lat, lng: stop.location.lng }}>
                                                    <div>
                                                        <strong>{stop.name}</strong>
                                                    </div>
                                                </InfoWindow>
                                            )}
                                        </Marker>
                                    ))}
                                </>
                            )}
                        </MarkerClusterer>
                    ) : (
                        <>
                            {visibleStops.map((stop: DisplayStopsObject) => (
                                <Marker
                                    key={stop.tag}
                                    position={{ lat: stop.location.lat, lng: stop.location.lng }}
                                    onMouseOver={() => handleMarkerHover(stop.tag)}
                                    onMouseOut={handleMarkerMouseOut}
                                    onClick={() => updateSelectedStop(stop.tag, stop.location)}
                                >
                                    {hoveredStop === stop.tag && (
                                        <InfoWindow position={{ lat: stop.location.lat, lng: stop.location.lng }}>
                                            <div>
                                                <strong>{stop.name}</strong>
                                            </div>
                                        </InfoWindow>
                                    )}
                                </Marker>
                            ))}
                        </>
                    )}
                </>
            )}
        </>
    );
};

export default Stops;
