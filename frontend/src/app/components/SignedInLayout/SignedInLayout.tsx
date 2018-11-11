import React from "react";
import { SignedInRoutes } from "../../config/routes";
import ITokenContextData from "../../models/ITokenContextData";
import TokenContext from "../../contexts/TokenContext";
import User from "../../models/User";
import { EventBus } from "vertx3-eventbus-client";
import UserContext from "../../contexts/UserContext";
import Loading from "../Loading";
import { authenticationClient } from "../../config/clients";

interface IProp {
  readonly tokenContextData: ITokenContextData;
}

interface IState {
  loading: boolean;
  reconnecting: boolean;
  user?: User;
  eventBus?: EventBus;
}

class SignedInLayout extends React.Component<IProp, IState> {
  constructor(props: IProp) {
    super(props);

    this.state = {
      loading: true,
      reconnecting: false,
    };
  }

  public componentDidMount() {
    const token = this.props.tokenContextData.token;
    const logout = this.props.tokenContextData.logout;

    if (token) {
      authenticationClient
        .hello(token)
        .then((user: User) => {
          this.setState({ user });
        })
        .catch(() => {
          logout();
        })
        .then(() => {
          this.setState({ loading: false });
        });
    } else {
      logout();
    }
  }

  public render() {
    if (this.state.loading) {
      return <Loading />;
    } else {
      return (
        <UserContext.Provider
          value={{ user: this.state.user, eventBus: this.state.eventBus }}
        >
          <SignedInRoutes />
        </UserContext.Provider>
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
