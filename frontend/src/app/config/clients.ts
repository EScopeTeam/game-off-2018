import settings from "./settings";
import AuthenticationClient from "../clients/AuthenticationClient";
import PlayersClient from "../clients/PlayersClient";
import { IApiSettings } from "../models/ISettings";

const apiSettings: IApiSettings = settings.api;

export const authenticationClient: AuthenticationClient = new AuthenticationClient(
  apiSettings
);
export const playersClient: PlayersClient = new PlayersClient(apiSettings);
