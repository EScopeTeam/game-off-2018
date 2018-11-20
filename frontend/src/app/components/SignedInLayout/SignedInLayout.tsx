import React from "react";
import { SignedInRoutes } from "../../config/routes";
import ITokenContextData from "../../models/ITokenContextData";
import TokenContext from "../../contexts/TokenContext";
import User from "../../models/User";
import UserContext from "../../contexts/UserContext";
import WebsocketClient from "../../utils/WebsocketClient";
import EventBusStatus from "../../models/EventBusStatus";
import Reconnecting from "../Reconnecting";
import RestClient from "../../utils/RestClient";

interface IProp {
  readonly tokenContextData: ITokenContextData;
}

interface IState {
  user?: User;
  eventBusStatus: EventBusStatus;
}

class SignedInLayout extends React.Component<IProp, IState> {
  private _eventBus: WebsocketClient;

  private _restClient: RestClient;

  constructor(props: IProp) {
    super(props);

    const token = this.props.tokenContextData.token;
    const logout = this.props.tokenContextData.logout;

    if (token) {
      this._eventBus = new WebsocketClient(
        token,
        logout,
        this.changeEventBusStatus.bind(this)
      );
      this._restClient = new RestClient(token);
    } else {
      this._eventBus = new WebsocketClient(
        "",
        logout,
        this.changeEventBusStatus.bind(this)
      );
      this._restClient = new RestClient("");
      logout();
    }

    this.state = {
      eventBusStatus: EventBusStatus.CONNECTING,
    };
  }

  public componentDidMount() {
    this.connect();
  }

  public componentWillUnmount() {
    this._eventBus.close();
  }

  private changeEventBusStatus(eventBusStatus: EventBusStatus): void {
    this.setState({ eventBusStatus });
  }

  private connect(): void {
    this._eventBus
      .connect()
      .then((user: User) => {
        this.setState({ user });
      })
      .catch(error => {
        console.log(error);
        this.props.tokenContextData.logout();
      });
  }

  public render() {
    const user: User | undefined = this.state.user;
    const eventBusStatus: EventBusStatus = this.state.eventBusStatus;

    if (eventBusStatus === EventBusStatus.CONNECTED && user) {
      return (
        <UserContext.Provider
          value={{
            user,
            eventBus: this._eventBus,
            restClient: this._restClient,
          }}
        >
          <SignedInRoutes />
        </UserContext.Provider>
      );
    } else {
      return (
        <Reconnecting
          eventBusStatus={eventBusStatus}
          eventBus={this._eventBus}
        />
      );
    }
  }
}

export default (props: {}) => {
  return (
    <TokenContext.Consumer>
      {(tokenContextData: ITokenContextData) => (
        <SignedInLayout tokenContextData={tokenContextData} />
      )}
    </TokenContext.Consumer>
  );
};
