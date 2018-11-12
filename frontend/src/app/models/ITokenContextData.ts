export default interface ITokenContextData {
  readonly token?: string;

  readonly login: (token: string) => void;

  readonly logout: () => void;
}
