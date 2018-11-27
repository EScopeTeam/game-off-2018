import axios from "axios";
import { IApiSettings } from "../models/ISettings";
import ISignUpForm from "../models/ISignUpForm";

export default class AuthenticationClient {
  private _settings: IApiSettings;

  constructor(settings: IApiSettings) {
    this._settings = settings;
  }

  public async login(username: string, password: string): Promise<string> {
    const response = await axios.post(this._settings.rest.login, {
      username,
      password,
    });

    return response.data.token;
  }

  public async signUp(form: ISignUpForm): Promise<string> {
    const response = await axios.post(this._settings.rest.signUp, form);

    return response.data.token;
  }
}
