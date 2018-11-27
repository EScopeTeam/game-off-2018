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
    tabBarIcon: () => (
      <Image
        source={require("../../../assets/bichosScreenIcons/stand.png")}
        style={{ width: 50, height: 50 }}
      />
    ),
    ...navigationStyles,
  };

  public render() {
    return (
      <View>
        <StatusBar hidden={true} />
        <Text>ShopScreen</Text>
      </View>
    );
  }
}
