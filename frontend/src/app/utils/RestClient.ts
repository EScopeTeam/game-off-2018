import axios, { AxiosPromise } from "axios";

export default class RestClient {
  private _token: string;

  constructor(token: string) {
    this._token = token;
  }

  public get(url: string): AxiosPromise {
    return axios.get(url, { headers: this.getHeaders() });
  }

  public post(url: string, data?: any): AxiosPromise {
    return axios.post(url, data, { headers: this.getHeaders() });
  }

  public put(url: string, data?: any): AxiosPromise {
    return axios.put(url, data, { headers: this.getHeaders() });
  }

  public delete(url: string): AxiosPromise {
    return axios.delete(url, { headers: this.getHeaders() });
  }

  private getHeaders(): { [key: string]: string } {
    return { Authorization: "Bearer " + this._token };
  }
}
