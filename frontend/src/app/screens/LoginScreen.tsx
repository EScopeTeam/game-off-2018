import React from "react";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "./../config/globalStyles";
import { t } from "../config/i18n";
import LoginForm from "../components/LoginForm";


interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class LoginScreen extends React.Component<IProp> {
  public static navigationOptions: NavigationScreenOptions = {
    title: t("login:title"),
    ...navigationStyles,
  };

  public render() {
    return <LoginForm navigation={this.props.navigation} />;
  }
}
