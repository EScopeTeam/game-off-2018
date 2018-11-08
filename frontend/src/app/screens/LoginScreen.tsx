import React from "react";
import { Button, View, Text } from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "./../config/globalStyles";

interface IProp {
  navigation: NavigationScreenProp<any, any>;
}

export default class LoginScreen extends React.Component<IProp, {}> {
  public static navigationOptions: NavigationScreenOptions = {
    title: "Login",
    ...navigationStyles,
  };

  public render() {
    const navigation = this.props.navigation;

    return (
      <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
        <Text>Login</Text>
        <Button title="SIGNUP" onPress={() => navigation.navigate("Signup")} />
      </View>
    );
  }
}
