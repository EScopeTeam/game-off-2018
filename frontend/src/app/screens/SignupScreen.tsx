import React from "react";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "./../config/globalStyles";
import { t } from "../config/i18n";
import SignUpForm from "../components/SignUpForm";

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class SignupScreen extends React.Component<IProp>{
  public static navigationOptions: NavigationScreenOptions = {
    title: t("signIn:title"),
    ...navigationStyles,
  };

  public render() {
    return <SignUpForm navigation={this.props.navigation} />;
  }
}
