import {
  createBottomTabNavigator,
  createStackNavigator,
} from "react-navigation";
import BichosScreen from "../screens/BichosScreen";
import LoginScreen from "../screens/LoginScreen";
import SignupScreen from "../screens/SignupScreen";

export const SignedInRoutes = createBottomTabNavigator({
  Bichos: BichosScreen,
});

export const SignedOutRoutes = createStackNavigator({
  Login: LoginScreen,
  Signup: SignupScreen,
});
