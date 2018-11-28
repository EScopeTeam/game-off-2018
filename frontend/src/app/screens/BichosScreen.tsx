import React from "react";
import {
  View,
  StatusBar
} from "react-native";
import { NavigationScreenProp } from "react-navigation";
import { BichosRoute } from "../config/routes";


interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}


export default class BichosScreen extends React.Component<IProp> {
  public render() {
    return (
      <View style={{ flex: 1 }}>
        <StatusBar hidden={true} />
        <BichosRoute />
      </View>
    );
  }
}


