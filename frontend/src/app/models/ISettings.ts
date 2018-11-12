export interface IApiSettings {
  readonly websocket: { [key: string]: string };
  readonly rest: { [key: string]: string };
}

export interface ISettings {
  readonly api: IApiSettings;
}
