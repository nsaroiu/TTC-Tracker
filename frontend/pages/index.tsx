import { useLoadScript } from "@react-google-maps/api";
import Map from "../components/map";
import {Libraries} from "@react-google-maps/api/src/utils/make-load-script-url";

const libraries: Libraries = ["places"];

export default function Home() {
  const { isLoaded } = useLoadScript({
    googleMapsApiKey: process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY,
    libraries: libraries,
  });

  if (!isLoaded) return <div>Loading...</div>;
  return <Map />;
}
