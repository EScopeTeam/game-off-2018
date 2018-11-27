import React from "react";
import { Image } from "react-native";
import {
  createMaterialTopTabNavigator,
  createStackNavigator,
} from "react-navigation";
import BichosScreen from "../screens/BichosScreen";
import LoginScreen from "../screens/LoginScreen";
import SignupScreen from "../screens/SignupScreen";
import BattleScreen from "../screens/BattleScreen";
import BichosInfoScreen from "../screens/BichosInfoScreen";
import WorldScreen from "../screens/WorldScreen";
import InventoryScreen from "../screens/InventoryScreen";
import ShopScreen from "../screens/ShopScreen";


export const BichosRoute = createMaterialTopTabNavigator(
  {
    Shop: {
      screen: ShopScreen,
    },
    Inventory: {
      screen: InventoryScreen,
    },
    Word: {
      screen: WorldScreen,
    },
    Battle: {
      screen: BattleScreen,
    },
    BichosInfo: {
      screen: BichosInfoScreen,
    },
    
  },
  {
    initialRouteName: 'Word',
    animationEnabled: true,
    swipeEnabled: true,
    tabBarPosition: 'bottom',
    tabBarOptions: {
      activeTintColor: "#4d646d",
      style: {
        backgroundColor: "#35464d",
        height: 60,
      },
      indicatorStyle: {
        height: 0
      },
      showLabel: false,
    },
    
  },
);

export const SignedOutRoutes = createStackNavigator({
  Login: {
    screen: BichosScreen,
    navigationOptions: {
      header: null,
    },
  },
  Signup: SignupScreen,
  Bichos: BichosRoute,
});
