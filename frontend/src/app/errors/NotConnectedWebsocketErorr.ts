export default class NotConnectedWebsocketError extends Error {
  constructor(msg: string) {
    super(msg);
  }
}
