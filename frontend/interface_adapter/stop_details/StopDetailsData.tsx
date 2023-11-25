interface StopDetailsOutputData {
    stopName: string;
    routeTagsToDir: { [key: string]: {[key:string]: string} };
    //routeTagsToDir: maps route tags to a mapping of direction tags to direction names
}

export default StopDetailsOutputData;
