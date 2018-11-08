import User from "../models/User";
import { getToken } from "./../utils/authenticationHelper";
import { IApiSettings } from "../models/ISettings";

export default class PlayersClient {
  private _settings: IApiSettings;

  constructor(settings: IApiSettings) {
    this._settings = settings;
  }

  public async getCurrentUser(): Promise<User> {
    const token: string = getToken();
    const result: User = new User(
      token,
      "test",
      "test@test.com",
      "img.gif",
      []
    );
    // TODO FETCH USER
    return result;
  }
}
