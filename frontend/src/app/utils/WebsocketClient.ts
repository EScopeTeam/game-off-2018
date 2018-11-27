import EventBusClient, { EventBus } from "../../../lib/vertx-eventbus";
import User from "../models/User";
import env from "../config/env.conf";
import NotConnectedWebsocketError from "../errors/NotConnectedWebsocketErorr";
import EventBusStatus from "../models/EventBusStatus";
import settings from "../config/settings";

const GOAWAY_ERROR_CODE: number = 3000;
const NORMAL_CLOSURE_CODE: number = 1000;
const MAX_RECONNECT: number = 5;
const TIME_BETWEEN_RECONNECTION: number = 5000;

export default class WebsocketClient {
  private _token: string;

  private _logout: () => void;

  private _changeStatus: (s: EventBusStatus) => void;

  private _eventBus?: EventBus;

  private _reconnects: number = 0;

  private _reconnectId?: number;

  private _connected: boolean = false;

  constructor(
    token: string,
    logout: () => void,
    changeStatus: (s: EventBusStatus) => void
  ) {
    this._token = token;
    this._logout = logout;
    this._changeStatus = changeStatus;
  }

  public connect(): Promise<User> {
    this._reconnects++;
    this._changeStatus(EventBusStatus.CONNECTING);
    this.clean();

    return new Promise<User>((resolve, reject) => {
      this._eventBus = new EventBusClient(env.WEBSOCKET_URL);
      this._eventBus.defaultHeaders = {
        Authorization: "Bearer " + this._token,
      };
      this._eventBus.onerror = (error: Error) => reject(error);
      this._eventBus.onopen = () => {
        this._connected = true;
        this._reconnects = 0;

        this.send(settings.api.websocket.hello, {})
          .then((user: User) => {
            this._changeStatus(EventBusStatus.CONNECTED);
            resolve(user);
          })
          .catch((error: Error) => {
            this._changeStatus(EventBusStatus.DISCONNECTED);
            reject(error);
          });
      };
      this._eventBus.onclose = (e: CloseEvent) => {
        this._connected = false;

        if (!e || e.code !== NORMAL_CLOSURE_CODE) {
          this._changeStatus(EventBusStatus.DISCONNECTED);

          if (e && e.code === GOAWAY_ERROR_CODE) {
            this._logout();
          } else if (this._reconnects < MAX_RECONNECT) {
            this._reconnectId = setTimeout(
              this.connect.bind(this),
              TIME_BETWEEN_RECONNECTION
            );
          }
        }
      };
    });
  }

  private clean(): void {
    if (this._reconnectId) {
      clearTimeout(this._reconnectId);
    }
  }

  public close(): void {
    this.clean();
    if (this._eventBus && this._connected) {
      this._eventBus.close();
    }
  }

  public send(address: string, message: any): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      if (this._eventBus) {
        this._eventBus.send(
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
      } else {
        reject(new NotConnectedWebsocketError(""));
      }
    });
  }

  public publish(address: string, message: any): void {
    if (this._eventBus) {
      this._eventBus.publish(address, message, null);
    } else {
      throw new NotConnectedWebsocketError("");
    }
  }

  public listen(
    address: string,
    callback: (error: Error, msg: any) => void
  ): void {
    if (this._eventBus) {
      this._eventBus.registerHandler(address, null, callback);
    } else {
      throw new NotConnectedWebsocketError("");
    }
  }

  public unlisten(
    address: string,
    callback: (error: Error, msg: any) => void
  ): void {
    if (this._eventBus) {
      this._eventBus.unregisterHandler(address, null, callback);
    } else {
      throw new NotConnectedWebsocketError("");
    }
  }
}
