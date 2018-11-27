import IUser from "./IUser";
import WebsocketClient from "../utils/WebsocketClient";
import RestClient from "../utils/RestClient";

export default interface IUserContextData {
  readonly user: IUser;

  readonly eventBus: WebsocketClient;

  readonly restClient: RestClient;
}
