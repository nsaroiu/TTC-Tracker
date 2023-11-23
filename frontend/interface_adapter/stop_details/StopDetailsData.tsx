interface StopDetailsOutputData {
    stopName: string;
    routeTagsToDir: { [key: string]: {[key:string]: string} };
}

export default StopDetailsOutputData;
