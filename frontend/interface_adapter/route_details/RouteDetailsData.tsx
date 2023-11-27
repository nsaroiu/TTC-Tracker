import LatLngLiteral = google.maps.LatLngLiteral;
export interface RouteDetailsOutputData {
    shape: Location[];
    dirName: string;
}

export interface Location {
    latitude: number;
    longitude: number;
}

export interface RouteData {
    shape: LatLngLiteral[];
    dirName: string;
}

export interface RouteParam {
    routeTag: string;
    dirTag: string;
}

