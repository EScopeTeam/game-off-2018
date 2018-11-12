import React from "react";
import ITokenContextData from "../models/ITokenContextData";

const defaultValues: ITokenContextData = {
  login: (token: string) => {
    throw new Error("User cannot be logged in before initialize Layout.");
  },
  logout: () => {
    throw new Error("User cannot be logged out before initialize Layout.");
  },
};
export default React.createContext(defaultValues);
