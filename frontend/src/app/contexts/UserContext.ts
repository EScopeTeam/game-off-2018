import React from "react";
import IUserContextData from "../models/IUserContextData";
import IUser from "../models/IUser";
import WebsocketClient from "../utils/WebsocketClient";
import RestClient from "../utils/RestClient";

const guest: IUser = {
  userId: "",
  username: "guest",
  coins: 0.0,
  experience: 0,
  email: "guest@guest.com",
  active: false,
};
const defaultValues: IUserContextData = {
  user: guest,
  eventBus: new WebsocketClient(
    "",
    () => {
      /**/
    },
    () => {
      /**/
    }
  ),
  restClient: new RestClient(""),
};
export default React.createContext(defaultValues);
