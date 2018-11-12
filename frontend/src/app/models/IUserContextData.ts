import User from "./User";
import WebsocketClient from "../utils/WebsocketClient";

export default interface IUserContextData {
  readonly user: User;

  readonly eventBus: WebsocketClient;
}
