import React from "react";
import {
  Image,
} from "react-native";
import {
  createBottomTabNavigator,
  createStackNavigator,
  TabBarBottom
} from "react-navigation";
import BichosScreen from "../screens/BichosScreen";
import LoginScreen from "../screens/LoginScreen";
import SignupScreen from "../screens/SignupScreen";
import BattleScreen from "../screens/BattleScreen";
import BichosInfoScreen from "../screens/BichosInfoScreen";
import EggScreen from "../screens/EggScreen";
import InventoryScreen from "../screens/InventoryScreen";
import ShopScreen from "../screens/ShopScreen";

export const BichosRoute = createBottomTabNavigator({
  Shop: {
    screen: ShopScreen,

  },
  Inventory: {
    screen: InventoryScreen,

  },
  Battle: {
    screen: BattleScreen,

  },
  BichosInfo: {
    screen: BichosInfoScreen,

  },
  Egg: {
    screen: EggScreen,

  },
  tabBarComponent: TabBarBottom,
  tabBarPosition: 'bottom',
  animationEnabled: false,
  swipeEnabled: false,
});

export const SignedOutRoutes = createStackNavigator({
  Login: LoginScreen,
  Signup: SignupScreen
});
