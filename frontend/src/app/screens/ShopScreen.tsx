import React from "react";
import { Text, Image, View, StatusBar } from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "../config/globalStyles";

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class ShopScreen extends React.Component<IProp> {
  public static navigationOptions: NavigationScreenOptions = {
    header: null,
    ...navigationStyles,
  };

  public render() {
    return (
      <View style={{ flex: 1, backgroundColor: "#69b0bb"}}>
        <StatusBar hidden={true} />
        <Text>ShopScreen</Text>
      </View>
    );
  }
}
