import React, {useState, useMemo, useCallback, useRef, useEffect} from "react";

import {
    GoogleMap,
} from "@react-google-maps/api";

import {DisplayStopsData} from "../interface_adapter/display_stops/StopsData";

import StopsController from "../interface_adapter/display_stops/StopsController";
import Stops from "./stops";

import StopDetailsController from "../interface_adapter/stop_details/StopDetailsController";
import StopDetails from "./stop_details";
import StopDetailsData from "../interface_adapter/stop_details/StopDetailsData";

type LatLngLiteral = google.maps.LatLngLiteral;
type MapOptions = google.maps.MapOptions

const Map: React.FC = () => {
    const mapRef = useRef<google.maps.Map>();
    const center = useMemo<LatLngLiteral>(
        () => ({lat: 43.6532, lng: -79.3832}),
        []
    );
    const [mapZoom, setZoom] = useState<number>(10);
    const {getStops} = StopsController();
    const [stops, setStops] = useState<DisplayStopsData>();
    const [visibleStops, setVisibleStops] = useState<DisplayStopsData>({});
    useEffect(() => {
        const updateVisibleStops = () => {
            if (mapRef.current) {
                const bounds = mapRef.current.getBounds();
                const newZoom = mapRef.current.getZoom();
                if (newZoom) {
                    setZoom(newZoom);
                }
                console.log(bounds, newZoom)
                let filteredStops: DisplayStopsData = {};
                if (newZoom) {
                    if (newZoom >= 14.5) {
                        filteredStops = Object.keys(stops || {}).reduce((acc, key) => {
                            if (!stops) {
                                return acc;
                            }
                            const stop = stops[key];
                            if (bounds && bounds.contains({lat: stop.lat, lng: stop.lng})) {
                                acc[key] = stop;
                            }
                            return acc;
                        }, {} as DisplayStopsData);
                    }
                    console.log(filteredStops);

                    setVisibleStops(filteredStops)
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

    const {getStopDetails} = StopDetailsController();
    const [selectedStop, setSelectedStop] = useState<string | null>(null);
    const [stopDetails, setStopDetails] = useState<StopDetailsData | null>(null);
    const handleStopClick = (stopTag: string) => {
        setSelectedStop(stopTag);
    };

    useEffect(() => {
        if (selectedStop) {
            getStopDetails(selectedStop).then((stopDetails) => {
                if (!stopDetails) {
                    return;
                }
                setStopDetails(stopDetails);
            });
        }
    }, [selectedStop]);



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
                {selectedStop && stopDetails && (
                    <StopDetails stopDetails={stopDetails}/>
                )}
            </div>
            <div className="map">
                <GoogleMap
                    zoom={10}
                    center={center}
                    mapContainerClassName="map-container"
                    options={options}
                    onLoad={onLoad}
                    onClick={() => setSelectedStop(null)}
                >
                    <Stops visibleStops={visibleStops} mapZoom={mapZoom} updateSelectedStop={handleStopClick}/>

                </GoogleMap>
            </div>
        </div>
    );
}

export default Map;