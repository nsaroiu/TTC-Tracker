import React from "react";
import Link from "next/link";

const HomePage: React.FC = () => {
    return (
        <div className="home-container">
            <h1 className="title">TTC Tracker</h1>
            <div className="button-container">
                <Link href="/map">
                    <a className="continue-button">Continue</a>
                </Link>
            </div>
        </div>
    );
};

export default HomePage;
