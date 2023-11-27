import LatLngLiteral = google.maps.LatLngLiteral;

export interface Location {
    latitude: number;
    longitude: number;
}

export interface StopObject {
    name: string;
    tag: string;
    location: Location;
}

export interface DisplayStopsObject {
    name: string;
    tag: string;
    location: LatLngLiteral;
}

export interface StopsData {
    stops: StopObject[];
}

export interface DisplayStopsData extends Array<DisplayStopsObject> {}
