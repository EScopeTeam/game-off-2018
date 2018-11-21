import {
  createBottomTabNavigator,
  createStackNavigator,
} from "react-navigation";
import BichosScreen from "../screens/BichosScreen";
import LoginScreen from "../screens/LoginScreen";
import SignupScreen from "../screens/SignupScreen";
import BattleScreen from "../screens/BattleScreen";
import BichosInfoScreen from "../screens/BichosInfoScreen";
import EggScreen from "../screens/EggScreen";
import InventoryScreen from "../screens/InventoryScreen";
import ShopScreen from "../screens/ShopScreen";

export const SignedInRoutes = createBottomTabNavigator({
  Bichos: BichosScreen,
});

export const SignedOutRoutes = createStackNavigator({
  Login: LoginScreen,
  Signup: SignupScreen,
  Shop: ShopScreen,
  Inventory: InventoryScreen,
  Battle: BattleScreen,
  BichosInfo: BichosInfoScreen,
  Egg: EggScreen
});
