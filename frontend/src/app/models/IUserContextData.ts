import User from "./User";
import WebsocketClient from "../utils/WebsocketClient";
import RestClient from "../utils/RestClient";

export default interface IUserContextData {
  readonly user: User;

  readonly eventBus: WebsocketClient;

  readonly restClient: RestClient;
}
