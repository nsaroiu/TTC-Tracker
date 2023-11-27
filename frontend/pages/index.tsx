import { useLoadScript } from "@react-google-maps/api";
import HomePage from "./HomePage";
import Map from "./map";
import { Libraries } from "@react-google-maps/api/src/utils/make-load-script-url";
import React from "react";

const libraries: Libraries = ["places"];

export default function Home() {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY,
    libraries: libraries,
  });

  const [clicked, setClicked] = React.useState<boolean>(false);

  const handleClick = (clicked: boolean) => {
    setClicked(clicked);
  }

  if (!isLoaded) return <div>Loading...</div>;
  return (
      <>
        {clicked ? <Map /> : <HomePage updateClicked={handleClick} />}
      </>
  );
}
