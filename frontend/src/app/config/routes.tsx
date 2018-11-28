import React from "react";
import { Image, StyleSheet } from "react-native";
import {
  createMaterialTopTabNavigator,
  createStackNavigator,

} from "react-navigation";
import LoginScreen from "../screens/LoginScreen";
import SignupScreen from "../screens/SignupScreen";
import BattleScreen from "../screens/BattleScreen";
import BichosInfoScreen from "../screens/BichosInfoScreen";
import WorldScreen from "../screens/WorldScreen";
import InventoryScreen from "../screens/InventoryScreen";
import ShopScreen from "../screens/ShopScreen";


export const SignedInRoutes = createMaterialTopTabNavigator(
  {
    Shop: {
      screen: ShopScreen,
      navigationOptions: {
        tabBarIcon: (options: { focused: boolean }) => (
          <Image
            source={require("../../../assets/bichosScreenIcons/stand.png")}
            style={options.focused ? styles.activeBottom : styles.inactiveBottom}
          />
        )
      }
    },
    Inventory: {
      screen: InventoryScreen,
      navigationOptions: {
        tabBarIcon: (options: { focused: boolean }) => (
          <Image
            source={require("../../../assets/bichosScreenIcons/backpack.png")}
            style={options.focused ? styles.activeBottom : styles.inactiveBottom}
          />
        )
      }
    },
    Word: {
      screen: WorldScreen,
      navigationOptions: {
        tabBarIcon: (options: { focused: boolean }) => (
          <Image
            source={require("../../../assets/bichosScreenIcons/world.png")}
            style={options.focused ? styles.activeBottom : styles.inactiveBottom}
          />
        )
      }
    },
    Battle: {
      screen: BattleScreen,
      navigationOptions: {
        tabBarIcon: (options: { focused: boolean }) => (
          <Image
            source={require("../../../assets/bichosScreenIcons/swords.png")}
            style={options.focused ? styles.activeBottom : styles.inactiveBottom}
          />
        )
      }
    },
    BichosInfo: {
      screen: BichosInfoScreen,
      navigationOptions: {
        tabBarIcon: (options: { focused: boolean }) => (
          <Image
            source={require("../../../assets/bichosScreenIcons/monster.png")}
            style={options.focused ? styles.activeBottom : styles.inactiveBottom}
          />
        )
      }
    },

  },
  {
    initialRouteName: 'Word',
    animationEnabled: true,
    swipeEnabled: true,
    tabBarPosition: 'bottom',
    tabBarOptions: {
      showIcon: true,
      pressColor: "#35464d",
      style: {
        backgroundColor: "#35464d",
        height: 60,
      },
      iconStyle: {
        marginVertical: 5
      },
      indicatorStyle: {
        height: 0
      },
      tabStyle: {
        borderLeftWidth: 1,
        borderLeftColor: '#B6B6B5',
        backgroundColor: "#899aa3"
      },
      showLabel: false,
    },
  },
);

export const SignedOutRoutes = createStackNavigator({
  Login: {
    screen: LoginScreen,
    navigationOptions: {
      header: null,
    },
  },
  Signup: {
    screen: SignupScreen,
    navigationOptions: {
      header: null,
    },
  }
});

const styles = StyleSheet.create({
  activeBottom: {
    width: 55,
    height: 55
  },
  inactiveBottom: {
    width: 50,
    height: 50
  }
});
