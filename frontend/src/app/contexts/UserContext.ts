import React from "react";
import { IUserContextData } from "../models/IUserContextData";

const defaultValues: IUserContextData = {
  login: u => {
    throw new Error("User cannot be logged in before initialize Layout.");
  },
  logout: () => {
    throw new Error("User cannot be logged out before initialize Layout.");
  },
};
export default React.createContext(defaultValues);
