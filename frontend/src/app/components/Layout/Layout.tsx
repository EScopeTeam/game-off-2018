import React from "react";
import User from "../../models/User";
import UserContext from "../../contexts/UserContext";
import Loading from "../Loading";
import { SignedInRoutes, SignedOutRoutes } from "../../config/routes";
import { playersClient } from "../../config/clients";
import { loadToken, removeToken } from "../../utils/authenticationHelper";

interface IState {
  loading: boolean;
  currentUser?: User;
}

export default class Main extends React.Component<{}, IState> {
  constructor(props: {}) {
    super(props);

    this.state = {
      loading: true,
    };
  }

  public componentDidMount(): void {
    loadToken().then(hasToken => {
      if (hasToken) {
        playersClient
          .getCurrentUser()
          .then(user => {
            this.setState({ loading: false });
            this.login(user);
          })
          .catch(() => {
            this.setState({ loading: false });
            this.logout();
          });
      } else {
        this.setState({ loading: false });
      }
    });
  }

  private login(currentUser: User): void {
    this.setState({ currentUser });
  }

  private logout(): void {
    removeToken();
    this.setState({ currentUser: undefined });
  }

  private getChildComponent(currentUser?: User) {
    if (this.state.loading) {
      return <Loading />;
    } else if (currentUser) {
      return (
        <UserContext.Provider
          value={{
            currentUser,
            login: this.login.bind(this),
            logout: this.logout.bind(this),
          }}
        >
          <SignedInRoutes />
        </UserContext.Provider>
      );
    } else {
      return <SignedOutRoutes />;
    }
  }

  public render() {
    const currentUser = this.state.currentUser;

    return (
      <UserContext.Provider
        value={{
          currentUser,
          login: this.login.bind(this),
          logout: this.logout.bind(this),
        }}
      >
        {this.getChildComponent(currentUser)}
      </UserContext.Provider>
    );
  }
}
