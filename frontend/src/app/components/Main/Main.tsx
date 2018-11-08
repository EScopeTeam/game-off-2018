import React from "react";
import { TabNavigator } from "react-navigation";
import User from "../../models/User";
import UserContext from "../../contexts/UserContext";
import Loading from "../Loading";
import { SignedInRoutes, SignedOutRoutes } from "../../config/routes";

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

  public componentDidMount() {
    setTimeout(() => {
      this.setState({ loading: false });
    }, 5000);
  }

  public render() {
    const currentUser = this.state.currentUser;

    if (this.state.loading) {
      return <Loading />;
    } else if (currentUser) {
      return (
        <UserContext.Provider value={{ currentUser: this.state.currentUser }}>
          <SignedInRoutes />
        </UserContext.Provider>
      );
    } else {
      return <SignedOutRoutes />;
    }
  }
}
