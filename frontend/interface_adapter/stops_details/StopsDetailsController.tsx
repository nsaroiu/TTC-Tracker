const StopsDetailsController = (stopName: string) => {
    const fetchStopDetails = async () => {
        try {
            const response = await fetch('http://localhost:8080/stopsdetails', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ stopTag: stopName }),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            return await response.json();
            // Do something with the data, e.g., update state, show in UI, etc.
        } catch (error: any) {
            console.error('Error fetching data:', error.message);
            // Handle error, e.g., show an error message to the user
        }
    };

    const getStopDetails = async (): Promise<any | undefined> => {
        try {
            // Wait for the fetchData promise to resolve
            return await fetchStopDetails();
        } catch (error: any) {
            // Handle errors if the fetchData promise is rejected
            console.error('Error fetching data:', error.message);
        }
    };

    return {
        getStopDetails
    }
};

export default StopsDetailsController;
