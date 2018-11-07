import Item from "./Item";

export default class User {
  public readonly token: string;

  public readonly username: string;

  public readonly email: string;

  public readonly avatarUrl: string;

  private _items: Item[];

  constructor(
    token: string,
    username: string,
    email: string,
    avatarUrl: string,
    items: Item[]
  ) {
    this.token = token;
    this.username = username;
    this.email = email;
    this.avatarUrl = avatarUrl;
    this._items = items;
  }

  get items(): Item[] {
    return this._items;
  }
}
