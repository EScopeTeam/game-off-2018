import React from "react";
import BichosScreenLayout from "./../components/BichosScreenLayout/BichosScreenLayout"
import { NavigationScreenProp } from "react-navigation";

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class LoginScreen extends React.Component<IProp> {
  public render() {
    return <BichosScreenLayout lvl={5} exp={1000} coins={25000} swords={2500} navigation={this.props.navigation}/>;
  }
}
