import LatLngLiteral = google.maps.LatLngLiteral;

export type Stops = {
    [index: string]: {
        latitude: number;
        longitude: number;
    }
}