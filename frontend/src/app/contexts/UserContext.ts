import React from "react";
import IUserContextData from "../models/IUserContextData";
import User from "../models/User";
import WebsocketClient from "../utils/WebsocketClient";
import RestClient from "../utils/RestClient";

const guest: User = new User("", "guest", "guest@guest.com", "guest.jpg", []);
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
