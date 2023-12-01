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

import RouteDetailsController from "../interface_adapter/route_details/RouteDetailsController";
import RouteDetails from "./route_details";
import {RouteData, RouteParam} from "../interface_adapter/route_details/RouteDetailsData";

import VehicleController from "../interface_adapter/vehicle_locations/VehicleController";
import VehicleLocations from "./vehicle_locations";

type LatLngLiteral = google.maps.LatLngLiteral;
type MapOptions = google.maps.MapOptions

const Map: React.FC = () => {
    const mapRef = useRef<google.maps.Map>();
    const center = useMemo<LatLngLiteral>(
        () => ({lat: 43.6532, lng: -79.3832}),
        []
    );
    const [mapZoom, setZoom] = useState<number>(15);
    const {getStops} = StopsController();
    const [stops, setStops] = useState<DisplayStopsData>();
    const [visibleStops, setVisibleStops] = useState<DisplayStopsData>([]);
    useEffect(() => {
        const updateVisibleStops = () => {
            if (mapRef.current) {
                const bounds = mapRef.current.getBounds();
                const newZoom = mapRef.current.getZoom();
                if (newZoom) {
                    setZoom(newZoom);
                }
                let filteredStops: DisplayStopsData = [];
                if (newZoom) {
                    if (newZoom >= 14.5) {
                        for (let i = 0; i < stops!.length; i++) {
                            const stop = stops![i];
                            if (bounds && bounds.contains({lat: stop.location.lat, lng: stop.location.lng})) {
                                filteredStops.push(stop);
                            }
                        }
                    }

                    setVisibleStops(filteredStops);
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
        setRouteDetails(null);
        setSelectedRouteParam(null)
        setVehicleLocations(null)
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

    const {getVehicleLocations} = VehicleController();
    const [vehicleLocations, setVehicleLocations] = useState<LatLngLiteral[]|null>([]);
    const [selectedRouteParam, setSelectedRouteParam] = useState<RouteParam | null>(null);
    const {getRouteDetails} = RouteDetailsController();
    const [routeDetails, setRouteDetails] = useState<RouteData | null>(null);
    const handleRouteChange = (routeParam: RouteParam) => {
        setSelectedRouteParam(routeParam);
    };

    useEffect(() => {
        if (selectedRouteParam) {
            getRouteDetails(selectedRouteParam).then((routeDetails) => {
                if (!routeDetails) {
                    return;
                }
                setRouteDetails(routeDetails);
            });
        }
    }, [selectedRouteParam]);

    useEffect(() => {
        // Call getVehicleLocations every 30 seconds if selectedRouteParam is not null
        const intervalId = setInterval(() => {
            if (selectedRouteParam) {
                getVehicleLocations(selectedRouteParam.routeTag).then((vehicleLocations) => {
                    if (!vehicleLocations) {
                        return;
                    }
                    setVehicleLocations(vehicleLocations);
                });
            }
        }, 5000);

        // Cleanup the interval when the component is unmounted
        return () => {
            clearInterval(intervalId);
        };
    }, [getVehicleLocations, selectedRouteParam]);

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
            setZoom(mapRef.current.getZoom() || 15);
        }
    }, []);


    const handleGetStops = useCallback(() => {
        getStops().then((stops) => {
                setStops(stops);
            }
        );
    }, []);


    // @ts-ignore
    return (
        <div className="container">
            <div className="controls">
                <button className="button" onClick={handleGetStops}>Get Stops</button>
                {selectedStop && stopDetails && (
                    <StopDetails stopDetails={stopDetails} UpdateSelectedRoute={handleRouteChange}/>
                )}
            </div>
            <div className="map">
                <GoogleMap
                    zoom={15}
                    center={center}
                    mapContainerClassName="map-container"
                    options={options}
                    onLoad={onLoad}
                    onClick={() => {setSelectedStop(null), setRouteDetails(null), setVehicleLocations(null)}}
                >
                    <Stops visibleStops={visibleStops} mapZoom={mapZoom} updateSelectedStop={handleStopClick}/>
                    {routeDetails && selectedStop && (
                        <RouteDetails routeDetails={routeDetails}/>
                    )}
                    {vehicleLocations && (
                        <VehicleLocations vehicleLocations={vehicleLocations}/>
                    )}
                </GoogleMap>
            </div>
        </div>
    );
}

export default Map;