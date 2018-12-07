import React from "react";
import { Text, Image, View } from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "../config/globalStyles";


interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class InventoryScreen extends React.Component<IProp> {
  public static navigationOptions: NavigationScreenOptions = {
    header: null,
    ...navigationStyles,
  };

  public render() {
    return (
      <View style={{flex:1, backgroundColor: "#ed8a19" }}>
        <Text>InventoryScreen</Text>
      </View>
    );
  }
}