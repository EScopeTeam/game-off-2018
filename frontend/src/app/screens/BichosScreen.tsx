import React from "react";
import {
  Animated,
  Dimensions,
  ScrollView,
  StyleSheet,
  Text,
  View,
  Image,
  StatusBar
} from "react-native";
import { NavigationScreenProp, createBottomTabNavigator } from "react-navigation";
import BoottomMenu from "../components/BottomMenu/BottomMenu";
import EggScreen from "./WorldScreen"
import ShopScreen from "../screens/ShopScreen"
import InventoryScreen from "../screens/InventoryScreen"
import BattleScreen from "../screens/BattleScreen"
import BichosInfoScreen from "../screens/BichosInfoScreen"
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


