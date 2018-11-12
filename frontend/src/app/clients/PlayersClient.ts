import { IApiSettings } from "../models/ISettings";

export default class PlayersClient {
  private _settings: IApiSettings;

  constructor(settings: IApiSettings) {
    this._settings = settings;
  }
}
