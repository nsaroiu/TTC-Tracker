export interface Vehicle{
    id: number;
    speed: number
    location: {
        latitude: number
        longitude: number
    }
    heading: number
    directionTag: string
}

export interface VehicleData{
    vehicles: Array<Vehicle>
}

