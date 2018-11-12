import React from "react";
import IUserContextData from "../models/IUserContextData";
import User from "../models/User";
import WebsocketClient from "../utils/WebsocketClient";

const guest: User = new User("", "guest", "guest@guest.com", "guest.jpg", []);
const defaultValues: IUserContextData = {
  user: guest,
  eventBus: new WebsocketClient(""),
};
export default React.createContext(defaultValues);
