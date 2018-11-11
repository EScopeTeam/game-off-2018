import User from "./User";
import { EventBus } from "vertx3-eventbus-client";

export default interface IUserContextData {
  readonly user?: User;

  readonly eventBus?: EventBus;
}
