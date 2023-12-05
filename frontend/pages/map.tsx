import React, {useState, useMemo, useCallback, useRef, useEffect} from "react";

import {
    GoogleMap, Marker,
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

import Predictions from "./predictions";
import PredictionsController from "../interface_adapter/predictions/PredictionsController";
import {PredictionsData} from "../interface_adapter/predictions/PredictionsData";

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
    const [visibleStops, setVisibleStops] = useState<DisplayStopsData>([]);
    const [stops, setStops] = useState<DisplayStopsData>();
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
    const [stopLocation, setStopLocation] = useState<LatLngLiteral | null>(null);
    const handleStopClick = (stopTag: string, location: LatLngLiteral) => {
        setRouteDetails(null);
        setSelectedRouteParam(null)
        setVehicleLocations(null)
        setSelectedStop(stopTag);
        setPredictions(null)
        setStopLocation(location);
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
                getVehicleLocations(selectedRouteParam.routeTag, selectedRouteParam.dirTag).then((vehicleLocations) => {
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

    const {getPredictions} = PredictionsController();
    const [predictions, setPredictions] = useState<PredictionsData | null>(null);
    const updatePredictions = useCallback(() => {
        if (selectedRouteParam && selectedStop) {
            getPredictions(selectedRouteParam.routeTag, selectedRouteParam.dirTag, selectedStop).then((predictions) => {
                if (!predictions) {
                    return;
                }
                setPredictions(predictions);
            });
        }
    }, [getPredictions, selectedRouteParam, selectedStop]);

    useEffect(() => {
        // Call getPredictions every 10 seconds if selectedRouteParam and selectedStop are not null
        updatePredictions();
        const intervalId = setInterval(updatePredictions, 30000);

        // Cleanup the interval when the component is unmounted
        return () => {
            clearInterval(intervalId);
        };
    }, [updatePredictions, selectedRouteParam, selectedStop]);




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

                <Predictions predictions={predictions}/>

            </div>
            <div className="map">
                <GoogleMap
                    zoom={15}
                    center={center}
                    mapContainerClassName="map-container"
                    options={options}
                    onLoad={onLoad}
                    onClick={() => {setSelectedStop(null), setRouteDetails(null), setVehicleLocations(null), setPredictions(null), setSelectedRouteParam(null)}}
                >
                    <Stops visibleStops={visibleStops} mapZoom={mapZoom} updateSelectedStop={handleStopClick}/>
                    {routeDetails && selectedStop && (
                        <RouteDetails routeDetails={routeDetails}/>
                    )}
                    {vehicleLocations && (
                        <VehicleLocations vehicleLocations={vehicleLocations}/>
                    )}
                    {selectedStop && (
                        <Marker
                            key={selectedStop}
                            position={stopLocation!}
                            icon={'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAACXBIWXMAAAsTAAALEwEAmpwYAAAE0UlEQVR4nO2YfUwbZRzHnyjGGLniJPtDY4zLEnW2galx6ECJc+weOmEL8+7m3P6wd6Jmk2V/LDHGjGVrBTKjDq5mClMzElxQN8buOjM2eW1BNsgM0cGWTZS9cX1l9Ar0Ws4cUCijtFfTKyXhm3yTJk2efD53v+eeXgFYylKWErOoDw6uVeu5Eo2Ba1TrrXc0euuo2mDlNQauX2PgzmsM3L6cL52rQaJFrR/cotZb/9AYrGK4ri1zdGM078VpvgkzujcuNDd4psz6uFrPnY0ELvWFUvt1zOhx4kaPGChm9NRhla5HFwR+VQn3vMZgvSUHPr3E6sBo/u9g+Jny/Xi5Oz2u8GkGu0Zt4Jxy4DV6zltA812h4T2BckTF6NNxgbe9A1XndHvq0vS3BTkCeYfdLRHgxak7cTUu42TH4fd2AopXthFdGQf+uRsOPueLoSZ58J7AnjiiKLwDg1kSfKA3iPzLr+/vvRkKPvOQ4wJO877oBHj/VprPUEzARqD1wQJSOTz3DvFJe28w/EultmsYzbujgccnx0jI+7H6W0XgnVjOChuO+u8VkGoloGvnXrZTgl9tsA1upt3lGO3Jyq8Ske2HxIfxck8mTvM/SFc4nMCm6vpmlCX9OaeoFTEXsGPoB6HgA7UR6FjZR0cbM0rtufOtgRv5PNzIj4aC31J5qRmylCgVZcii2AsQ8OfwAnDcSa6jIq2D0XzRvfBvHbndibKFvoAAZKlfFBBA/wwnMLT35TqxFtwfaZ3sYjEJN3oGpjfu164r8PSu4SB4ETJUb8wFbDhqnQ/e8X52h2BWVcpdC6P576Y2LQfrPu6fBT8hQLoUEIDekPA71vd4Wx8ZEcyIIQqBzzCaH9H+9HnPHPhJASHmAnYcHZojsHXDjbGGVM5nUYmCWVUldy3cyB/Nr6lpCwk/WZsSAn2z4dFh78nH+iT4SQHkX7ERJMnZA5uOmU6FgRchS15WQuBE0CPT56la2RmAD+quSOu8Wbf/Xci8Nx5BoCbmAjYC3R0QGNanN4WAFwWLalRoR/LnW+PAWaiFJp0nPDylzDngJNCnpJPYVZTZEgp+ZpRUfsGiOuZtS35VbFyeLF5cluI1J79mbVt++G3TtlsR4VnSD03UE0CJuHZnfSO0pnjDCYSq14y4i34t6IsEDyevvgkoFa9FtUYwq8ajgZfuyFcN6zrkwENJoF73ClAyghk5Ho0Acz69STY8Qx0HSkdsTVkmmJEBOfB9LU+2yoWHLHXzjRM7UkE8IrQjuZFGydGW2r3RpBuTBc9Q49BEauMCPy1hRirmgx9pSxkgTNsdsq8+Q1WAeEc0gQd9FuTSnCeOBbm780zB1ShGpwer3fNQ3AUmJDqSnxPMCB90kAkHG2CX7E3LkryWIVeBhYyvXfVhQKD2tzXTb1UyR4cEiRCfRXWyq3ml7MflVGtBouSv359N1bK66/JHh7q2vqEwBSRStExhBspSkR+bDCkoftr+30CG3CfjtP0UJGqyG4uTUIa6EAa++8WLhQ+ARA40kWkoS/lCzL0v57ROAxZDIEvWhBCoBosl6BlKPfH7ZubA8i/4gRVtUIaa/tcBZalmsNiCMmRRQGADS0Z82U/IzRwQWDSbNzjFxcX3oQx1Tqr0edaXS1kKSJj8B1PN2xbr8aR9AAAAAElFTkSuQmCC'}
                        >
                        </Marker>)}

                </GoogleMap>
            </div>
        </div>
    );
}

export default Map;