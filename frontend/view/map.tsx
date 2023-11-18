import React, {useState, useMemo, useCallback, useRef, useEffect} from "react";
import {
    GoogleMap,
    Marker,
    DirectionsRenderer,
    Circle,
    MarkerClusterer,
} from "@react-google-maps/api";

import {Stops} from "../interface_adapter/display_stops/StopsData";

import StopsController from "../interface_adapter/display_stops/StopsController";

type LatLngLiteral = google.maps.LatLngLiteral;
type DirectionsResult = google.maps.DirectionsResult;
type MapOptions = google.maps.MapOptions

export default function Map() {
    const mapRef = useRef<google.maps.Map>();
    const center = useMemo<LatLngLiteral>(
        () => ({lat: 43.6532, lng: -79.3832}),
        []
    );
    const [mapZoom, setZoom] = useState<number>(10);
    const {getStops} = StopsController();
    const [stops, setStops] = useState<Stops>();
    const [visibleStops, setVisibleStops] = useState<Array<LatLngLiteral>>([]);
    useEffect(() => {
        const updateVisibleStops = () => {
            if (mapRef.current) {
                const bounds = mapRef.current.getBounds();
                const newZoom = mapRef.current.getZoom();
                if (newZoom) {
                    setZoom(newZoom);
                }
                console.log(bounds, newZoom)
                let filteredStops: Stops = {};
                if (newZoom) {
                    if (newZoom >= 14.5) {
                        filteredStops = Object.keys(stops || {}).reduce((acc, key) => {
                            if (!stops) {
                                return acc;
                            }
                            const stop = stops[key];
                            if (bounds && bounds.contains({lat: stop.latitude, lng: stop.longitude})) {
                                acc[key] = stop;
                            }
                            return acc;
                        }, {} as Stops);
                    } else {
                        // Handle the case when zoom is less than 14.5
                    }
                    console.log(filteredStops);

                    const LatLngFilteredStops: LatLngLiteral[] = Object.keys(filteredStops).map((key) => ({
                        lat: filteredStops[key].latitude,
                        lng: filteredStops[key].longitude,
                    }));

                    setVisibleStops(LatLngFilteredStops);
                }
            }
        };


        // Call the function initially and attach it to the map's 'idle' event
        updateVisibleStops();
        if (mapRef.current) {
            mapRef.current.addListener("idle", updateVisibleStops);
        }

        // Cleanup the event listener when the component is unmounted
        return () => {
            if (mapRef.current) {
                google.maps.event.clearListeners(mapRef.current, "idle");
            }
        };
    }, [stops]);


    const options = useMemo<MapOptions>(
        () => ({
            mapId: "b181cac70f27f5e6",
            disableDefaultUI: true,
            clickableIcons: false,
        }),
        []
    );
    const onLoad = useCallback((map) => {
        mapRef.current = map;
        if (mapRef.current) {
            setZoom(mapRef.current.getZoom() || 10);
        }
    }, []);


    const handleGetStops = useCallback(() => {
        getStops().then((stops) => {
                setStops(stops);
                console.log(stops);
            }
        );
    }, []);


    // @ts-ignore
    return (
        <div className="container">
            <div className="controls">
                <button onClick={handleGetStops}>Get Stops</button>
            </div>
            <div className="map">
                <GoogleMap
                    zoom={10}
                    center={center}
                    mapContainerClassName="map-container"
                    options={options}
                    onLoad={onLoad}
                >
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


                </GoogleMap>
            </div>
        </div>
    );
}
