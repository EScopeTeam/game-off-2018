import React from "react";
import {
  Animated,
  Dimensions,
  ScrollView,
  StyleSheet,
  Text,
  View,
  StatusBar,
  TouchableOpacity,
  Image
} from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "./../config/globalStyles";
import { t } from "../config/i18n";
import BoottomMenu from "../components/BottomMenu/BottomMenu";
import EggScreen from "../screens/EggScreen";
import ShopScreen from "../screens/ShopScreen";
import InventoryScreen from "../screens/InventoryScreen";
import BattleScreen from "../screens/BattleScreen";
import BichosInfoScreen from "../screens/BichosInfoScreen";
import GenericTouchableOpacity from "../components/GenericTouchableOpacity/GenericTouchableOpacity"
import VerticalLine from "../components/VerticalLine/VerticalLine"

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

const SCREEN_WIDTH = Dimensions.get("window").width;

const xOffset = new Animated.Value(0);

function transitionAnimation(index: number) {
  return {
    transform: [
      { perspective: 800 },
      {
        scale: xOffset.interpolate({
          inputRange: [
            (index - 1) * SCREEN_WIDTH,
            index * SCREEN_WIDTH,
            (index + 1) * SCREEN_WIDTH,
          ],
          outputRange: [1, 1, 1],
        }),
      },
    ],
  };
}

export default class LoginScreen extends React.Component<IProp> {
  public static navigationOptions: NavigationScreenOptions = {
    header: null,
    ...navigationStyles,
  };

  public render() {
    return (
      <View style={{ flex: 1 }}>
        <StatusBar hidden={true} />
        <View style={{ flex: 17 }}>
          <Animated.ScrollView
            scrollEventThrottle={1}
            onScroll={Animated.event(
              [{ nativeEvent: { contentOffset: { x: xOffset } } }],
              { useNativeDriver: true }
            )}
            horizontal
            pagingEnabled
            showsHorizontalScrollIndicator={false}
            style={styles.scrollView}
          >
            <View style={{ width: SCREEN_WIDTH  }}>
              <Animated.View style={transitionAnimation(0)}>
                <ShopScreen navigation={this.props.navigation} />
              </Animated.View>
            </View>
            <View style={{ width: SCREEN_WIDTH }}>
              <Animated.View style={transitionAnimation(1)}>
                <InventoryScreen navigation={this.props.navigation} />
              </Animated.View>
            </View>
            <View style={{ width: SCREEN_WIDTH }}>
              <Animated.View style={transitionAnimation(2)}>
                <BattleScreen navigation={this.props.navigation} />
              </Animated.View>
            </View>
            <View style={{ width: SCREEN_WIDTH }}>
              <Animated.View style={transitionAnimation(3)}>
                <BichosInfoScreen navigation={this.props.navigation} />
              </Animated.View>
            </View>
            <View style={{ width: SCREEN_WIDTH }}>
              <Animated.View style={transitionAnimation(4)}>
                <EggScreen navigation={this.props.navigation} />
              </Animated.View>
            </View>
          </Animated.ScrollView>
        </View>
        <View
          style={{
            flex: 2,
            backgroundColor: "#35464d",
            flexDirection: "row",
            justifyContent: "space-around",
          }}
        >
          <GenericTouchableOpacity
            source={require("../../../assets/bichosScreenIcons/stand.png")}
            onPress={() => this.refs}
          />
          <VerticalLine />
          <GenericTouchableOpacity
            source={require("../../../assets/bichosScreenIcons/backpack.png")}
            onPress={() => this.props.navigation.navigate("Inventory")}
          />
          <VerticalLine />
          <TouchableOpacity
            style={{ position: "relative", top: -20 }}
            onPress={() => this.props.navigation.navigate("Battle")}
          >
            <Image
              source={require("../../../assets/bichosScreenIcons/swords.png")}
              style={{ width: 80, height: 80 }}
            />
          </TouchableOpacity>
          <VerticalLine />
          <GenericTouchableOpacity
            source={require("../../../assets/bichosScreenIcons/monster.png")}
            onPress={() => this.props.navigation.navigate("BichosInfo")}
          />
          <VerticalLine />
          <GenericTouchableOpacity
            source={require("../../../assets/bichosScreenIcons/easter.png")}
            onPress={() => this.props.navigation.navigate("Egg")}
          />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  scrollView: {
    flexDirection: "row",
    backgroundColor: "#00d4ff",
  },
});
