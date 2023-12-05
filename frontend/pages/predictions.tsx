import React, { useState, useEffect } from "react";
import { PredictionsData } from "../interface_adapter/predictions/PredictionsData";

interface PredictionsProps {
    predictions: PredictionsData | null;
}

const Predictions: React.FC<PredictionsProps> = ({ predictions }) => {
    const [currentPredictions, setCurrentPredictions] = useState<number[] | null>(null);

    useEffect(() => {
        // Clear current predictions when a new stop is selected
        setCurrentPredictions(null);

        // Update the state when new predictions are received
        if (predictions && predictions.predictionTimes) {
            setCurrentPredictions(predictions.predictionTimes.map((predictionTime) => Math.round(predictionTime)));
        }
        console.log(predictions);
        console.log(currentPredictions);
    }, [predictions]);

    // Implement your UI to display predictions in an info window
    return (
        <div>
            {currentPredictions !== [] && currentPredictions !== null && (
                <div>
                    <h3>Predictions</h3>
                    {currentPredictions.map((predictionTime, index) => (
                        <p key={index}>{predictionTime} minutes</p>
                    ))}
                </div>
            )}
        </div>
    );
}

export default Predictions;
