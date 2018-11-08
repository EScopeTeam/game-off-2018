import React from "react";
import { View, Text } from "react-native";
import { NavigationScreenOptions } from "react-navigation";
import { navigationStyles } from "./../config/globalStyles";

export default class SignupScreen extends React.Component {
  public static navigationOptions: NavigationScreenOptions = {
    title: "Signup",
    ...navigationStyles,
  };

  public render() {
    return (
      <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
        <Text>Signup</Text>
      </View>
    );
  }
}
