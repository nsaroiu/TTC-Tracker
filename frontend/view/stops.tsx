import {Marker, MarkerClusterer} from "@react-google-maps/api";

type LatLngLiteral = google.maps.LatLngLiteral;

const Stops = (visibleStops: Array<LatLngLiteral>) => {

    return (
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
    );

}

export default Stops;
