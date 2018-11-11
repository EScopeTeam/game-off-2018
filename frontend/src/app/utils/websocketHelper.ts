import User from "../models/User";
import EventBusClient, { EventBus } from "vertx3-eventbus-client";
import env from "../config/env.conf";
import NotConnectedWebsocketError from "../errors/NotConnectedWebsocketErorr";

let eb: EventBus | null = null;

export default {
  connect: (token: string): Promise<User> => {
    return new Promise<User>((resolve, reject) => {
      eb = new EventBusClient(env.WEBSOCKET_URL);
      (eb as any).defaultHeaders = {
        Authorization: "Bearer " + token,
      };
      eb.onerror = (error: Error) => reject(error);
      eb.onopen = () => {
        // TODO HELLO
        resolve();
      };
      eb.onclose = () => {
        // TODO reconnect or logout
      };
    });
  },
  send: (address: string, message: any): Promise<any> => {
    return new Promise<any>((resolve, reject) => {
      if (eb === null) {
        reject(new NotConnectedWebsocketError(""));
      } else {
        eb.send(
          address,
          message,
          null,
          (error: Error, callbackMessage: any) => {
            if (error) {
              reject(error);
            } else {
              resolve(callbackMessage);
            }
          }
        );
      }
    });
  },
  listen: (address: string, callback: (error: Error, msg: any) => void) => {
    if (eb === null) {
      throw new NotConnectedWebsocketError("");
    } else {
      eb.registerHandler(address, null, callback);
    }
  },
};
