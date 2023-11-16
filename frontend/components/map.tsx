import { useState, useMemo, useCallback, useRef, useEffect } from "react";
import {
  GoogleMap,
  Marker,
  DirectionsRenderer,
  Circle,
  MarkerClusterer,
} from "@react-google-maps/api";

import Places from "./places";
import Distance from "./distance";
import Stops from "./stops";

type LatLngLiteral = google.maps.LatLngLiteral;
type DirectionsResult = google.maps.DirectionsResult;
type MapOptions = google.maps.MapOptions
type ChildData = {
  [key: number]: {
    latitude: number;
    longitude: number;
  };
};

export default function Map() {
  const [office, setOffice] = useState<LatLngLiteral>();
  const [directions, setDirections] = useState<DirectionsResult>();
  const mapRef = useRef<GoogleMap>();
  const center = useMemo<LatLngLiteral>(
    () => ({ lat: 43.6532, lng: -79.3832 }),
    []
  );
  const [childData, setChildData] = useState<ChildData|null>(null);

  const handleChildData = (data: any) => {
    // Do something with the data in the parent component
    setChildData(data);
  };
  const [visibleStops, setVisibleStops] = useState<Array<LatLngLiteral>>([]);
  useEffect(() => {
    const updateVisibleStops = () => {
      if (mapRef.current) {
        const bounds = mapRef.current.getBounds();
        const zoom = mapRef.current.getZoom();
        console.log(bounds, zoom);
        let filteredStops: ChildData = [];
        if (zoom >= 14.5) {
           filteredStops = Object.values(childData || {}).filter(
              (stop) =>
                  bounds &&
                  bounds.contains({lat: stop.latitude, lng: stop.longitude})

          );
        } else {
        }
        console.log(filteredStops);
        const LatLngFilteredStops: LatLngLiteral[] = filteredStops.map((stop : Number) => ({
          lat: stop.latitude,
          lng: stop.longitude,
          }));

        setVisibleStops(LatLngFilteredStops);
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
  }, [childData]);

  const options = useMemo<MapOptions>(
    () => ({
      mapId: "b181cac70f27f5e6",
      disableDefaultUI: true,
      clickableIcons: false,
    }),
    []
  );
  const onLoad = useCallback((map) => (mapRef.current = map), []);
  const houses = useMemo(() => generateHouses(center), [center]);

  const fetchDirections = (house: LatLngLiteral) => {
    if (!office) return;

    const service = new google.maps.DirectionsService();
    service.route(
      {
        origin: house,
        destination: office,
        travelMode: google.maps.TravelMode.DRIVING,
      },
      (result, status) => {
        if (status === "OK" && result) {
          setDirections(result);
        }
      }
    );
  };

  return (
    <div className="container">
      <div className="controls">
        <h1>Commute?</h1>
        <Stops onData={handleChildData}/>
        {!office && <p>Enter the address of your office.</p>}
        {directions && <Distance leg={directions.routes[0].legs[0]} />}
      </div>
      <div className="map">
        <GoogleMap
          zoom={10}
          center={center}
          mapContainerClassName="map-container"
          options={options}
          onLoad={onLoad}
        >
          {directions && (
            <DirectionsRenderer
              directions={directions}
              options={{
                polylineOptions: {
                  zIndex: 50,
                  strokeColor: "#1976D2",
                  strokeWeight: 5,
                },
              }}
            />
          )}
          {visibleStops.length > 0 && (
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
          )}
          {office && (
            <>
              <Marker
                position={office}
                icon="https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png"
              />

              <MarkerClusterer>
                {(clusterer) =>
                  houses.map((house) => (
                    <Marker
                      key={house.lat}
                      position={house}
                      clusterer={clusterer}
                      onClick={() => {
                        fetchDirections(house);
                      }}
                    />
                  ))
                }
              </MarkerClusterer>

              <Circle center={office} radius={15000} options={closeOptions} />
              <Circle center={office} radius={30000} options={middleOptions} />
              <Circle center={office} radius={45000} options={farOptions} />
            </>
          )}
        </GoogleMap>
      </div>
    </div>
  );
}

const defaultOptions = {
  strokeOpacity: 0.5,
  strokeWeight: 2,
  clickable: false,
  draggable: false,
  editable: false,
  visible: true,
};
const closeOptions = {
  ...defaultOptions,
  zIndex: 3,
  fillOpacity: 0.05,
  strokeColor: "#8BC34A",
  fillColor: "#8BC34A",
};
const middleOptions = {
  ...defaultOptions,
  zIndex: 2,
  fillOpacity: 0.05,
  strokeColor: "#FBC02D",
  fillColor: "#FBC02D",
};
const farOptions = {
  ...defaultOptions,
  zIndex: 1,
  fillOpacity: 0.05,
  strokeColor: "#FF5252",
  fillColor: "#FF5252",
};

const generateHouses = (position: LatLngLiteral) => {
  const _houses: Array<LatLngLiteral> = [];
  for (let i = 0; i < 100; i++) {
    const direction = Math.random() < 0.5 ? -2 : 2;
    _houses.push({
      lat: position.lat + Math.random() / direction,
      lng: position.lng + Math.random() / direction,
    });
  }
  return _houses;
};
