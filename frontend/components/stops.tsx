import React from 'react';
import data from "@react-google-maps/api/src/components/drawing/Data";

const Stops = ({onData}: any) => {
    const fetchData = async () => {
        try {
            const response = await fetch('http://localhost:8080/stops'); // Replace with your API endpoint
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            return data;
            // Do something with the data, e.g., update state, show in UI, etc.
        } catch (error: any) {
            console.error('Error fetching data:', error.message);
            // Handle error, e.g., show an error message to the user
        }
    };

    const sendDataToParent = async () => {
        try {
            // Wait for the fetchData promise to resolve
            const data = await fetchData();

            // Call the function passed from the parent, and pass data as an argument
            onData(data);
        } catch (error: any) {
            // Handle errors if the fetchData promise is rejected
            console.error('Error fetching data:', error.message);
        }
    };



    return (
        <button onClick={sendDataToParent}>
            Click to Fetch Data
        </button>
    );

}

export default Stops;
