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
import EggScreen from "../screens/EggScreen"
import ShopScreen from "../screens/ShopScreen"
import InventoryScreen from "../screens/InventoryScreen"
import BattleScreen from "../screens/BattleScreen"
import BichosInfoScreen from "../screens/BichosInfoScreen"


interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}


export default class BichosScreen extends React.Component<IProp> {
  public render() {
    return (
      <View style={{ flex: 1 }}>
        <StatusBar hidden={true} />
        
      </View>
    );
  }
}


