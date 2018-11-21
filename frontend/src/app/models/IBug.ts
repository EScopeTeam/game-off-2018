import IBugImage from "./IBugImage";

export default interface IBug {
  bugRaceId: string;

  images: IBugImage[];
}
