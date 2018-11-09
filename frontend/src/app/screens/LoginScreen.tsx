import React from "react";
import { Button, View, Text, TextInput } from "react-native";
import {
  NavigationScreenProps,
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "./../config/globalStyles";
import UserContext from "./../contexts/UserContext";
import { authenticationClient, playersClient } from "../config/clients";
import { IUserContextData } from "../models/IUserContextData";
import { saveToken } from "../utils/authenticationHelper";
import i18n, { t } from "../config/i18n";

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

interface IState {
  username: string;
  password: string;
}

// TODO extract to a component
export default class LoginScreen extends React.Component<IProp, IState> {
  public static navigationOptions: NavigationScreenOptions = {
    title: t("login:title"),
    ...navigationStyles,
  };

  constructor(props: IProp) {
    super(props);

    this.state = {
      username: "",
      password: "",
    };
  }

  private submit(contextData: IUserContextData): void {
    authenticationClient
      .login(this.state.username, this.state.password)
      .then(token => {
        // TODO handle 401 error
        // TODO handle error in forms
        saveToken(token).then(() => {
          playersClient.getCurrentUser().then(user => contextData.login(user));
        });
      });
  }

  public render() {
    const navigation = this.props.navigation;

    return (
      <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
        <Text>Login {i18n.language}</Text>
        <TextInput
          onChangeText={username => this.setState({ username })}
          value={this.state.username}
          placeholder={t("login:username")}
        />
        <TextInput
          onChangeText={password => this.setState({ password })}
          value={this.state.password}
          placeholder={t("login:password")}
        />
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
