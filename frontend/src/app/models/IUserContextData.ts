import User from "./User";

export interface IUserContextData {
  readonly currentUser?: User;

  readonly login: (currentUser: User) => void;

  readonly logout: () => void;
}
