import LatLngLiteral = google.maps.LatLngLiteral;

export type StopsData = {
    [index: string]: {
        latitude: number;
        longitude: number;
    }
}