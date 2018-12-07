import React from "react";
import { Text,View } from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "../config/globalStyles";


interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class BichosInfoScreen extends React.Component<IProp> {
  public static navigationOptions: NavigationScreenOptions = {
    header: null,
    ...navigationStyles,
  };

  public render() {
    return (
      <View style={{ flex: 1, backgroundColor: "#ff5364"}}>
        <Text>BichosInfoScreen</Text>
      </View>
    );
  }
}