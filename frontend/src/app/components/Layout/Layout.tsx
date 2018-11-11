import React from "react";
import TokenContext from "../../contexts/TokenContext";
import Welcome from "../Welcome";
import { SignedOutRoutes } from "../../config/routes";
import SignedInLayout from "../SignedInLayout";
import { loadToken, removeToken } from "../../utils/authenticationHelper";

interface IState {
  loading: boolean;
  token?: string;
}

export default class Layout extends React.Component<{}, IState> {
  constructor(props: {}) {
    super(props);

    this.state = {
      loading: true,
    };
  }

  public componentDidMount(): void {
    loadToken()
      .then(token => {
        if (token !== null) {
          this.login(token);
        }
      })
      .catch(() => {
        removeToken();
      })
      .then(() => {
        this.setState({ loading: false });
      });
  }

  private login(token: string): void {
    this.setState({ token });
  }

  private logout(): void {
    removeToken();
    this.setState({ token: undefined });
  }

  private getChildComponent(token?: string) {
    if (this.state.loading) {
      return <Welcome />;
    } else if (token) {
      return <SignedInLayout />;
    } else {
      return <SignedOutRoutes />;
    }
  }

  public render() {
    const token = this.state.token;

    return (
      <TokenContext.Provider
        value={{
          token,
          login: this.login.bind(this),
          logout: this.logout.bind(this),
        }}
      >
        {this.getChildComponent(token)}
      </TokenContext.Provider>
    );
  }
}
