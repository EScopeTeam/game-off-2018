import React from "react";
import { Button, View, Text } from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "./../config/globalStyles";
import UserContext from "./../contexts/UserContext";
import { playersClient } from "../config/clients";
import { IUserContextData } from "../models/IUserContextData";
import { saveToken } from "../utils/authenticationHelper";

interface IProp {
  navigation: NavigationScreenProp<any, any>;
}

export default class LoginScreen extends React.Component<IProp, {}> {
  public static navigationOptions: NavigationScreenOptions = {
    title: "Login",
    ...navigationStyles,
  };

  private submit(contextData: IUserContextData): void {
    // TODO login
    saveToken("1234").then(() => {
      playersClient.getCurrentUser().then(user => contextData.login(user));
    });
  }

  public render() {
    const navigation = this.props.navigation;

    return (
      <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
        <Text>Login</Text>
        <UserContext.Consumer>
          {(contextData: IUserContextData) => (
            <Button title="LOGIN" onPress={() => this.submit(contextData)} />
          )}
        </UserContext.Consumer>
        <Button title="SIGNUP" onPress={() => navigation.navigate("Signup")} />
      </View>
    );
  }
}
