import React from "react";
import Link from "next/link";

const HomePage: React.FC = () => {
    return (
        <>
        <link href="https://fonts.cdnfonts.com/css/manolo-mono" rel="stylesheet"></link>
        <div className="home-container">
            <h1 className="title">TTC Tracker</h1>
            <div className="button-container">
                <Link href="/map">
                    <a className="continue-button">Continue</a>
                </Link>
            </div>
        </div>
        </>
    );
};

export default HomePage;
