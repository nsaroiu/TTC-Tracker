interface StopDetailsOutputData {
    stopName: string;
    routeTagsToWrapper: { [key: string]: Array<[string,{ [key:string]: string }]> };
}

export default StopDetailsOutputData;
