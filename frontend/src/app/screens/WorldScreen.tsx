import React from "react";
import { Text,Image } from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "../config/globalStyles";
import BichosScreenLayout from "../components/BichosScreenLayout/BichosScreenLayout";


interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class WorldScreen extends React.Component<IProp> {
  public static navigationOptions: NavigationScreenOptions = {
    header: null,
    ...navigationStyles,
  };

  public render() {
    return (
      <BichosScreenLayout lvl={5} exp={2500} coins={10000} swords={1500} navigation={this.props.navigation} />
    );
  }
}