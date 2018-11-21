import React from "react";
import { Text } from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "../config/globalStyles";


interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class EggScreen extends React.Component<IProp> {
  public static navigationOptions: NavigationScreenOptions = {
    header: null,
    ...navigationStyles,
  };

  public render() {
    return (
      <Text>EggScreen</Text>
    );
  }
}