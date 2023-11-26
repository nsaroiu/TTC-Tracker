import React from "react";

interface HomePageProps {
    updateClicked: (clicked: boolean) => void;
}

const HomePage: React.FC<HomePageProps> = ({updateClicked}) => {
    return (
        <>
        <link href="https://fonts.cdnfonts.com/css/manolo-mono" rel="stylesheet"></link>
        <div className="home-container">
            <h1 className="title">TTC Tracker</h1>
            <div className="button-container">
                <a className="continue-button" onClick={() => updateClicked(true)}>Continue</a>
            </div>
        </div>
        </>
    );
};

export default HomePage;
