import User from "../models/User";
import { IApiSettings } from "../models/ISettings";

export default class AuthenticationClient {
  private _settings: IApiSettings;

  constructor(settings: IApiSettings) {
    this._settings = settings;
  }
}
