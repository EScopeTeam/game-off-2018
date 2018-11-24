import React from "react";
import {
  Animated,
  Dimensions,
  ScrollView,
  StyleSheet,
  Text,
  View,
  StatusBar
} from "react-native";
import { NavigationScreenProp } from "react-navigation";
import BoottomMenu from "../components/BottomMenu/BottomMenu";
import EggScreen from "../screens/EggScreen"
import ShopScreen from "../screens/ShopScreen"
import InventoryScreen from "../screens/InventoryScreen"
import BattleScreen from "../screens/BattleScreen"
import BichosInfoScreen from "../screens/BichosInfoScreen"


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
            (index + 1) * SCREEN_WIDTH
          ],
          outputRange: [1, 1, 1]
        })
      }
    ]
  };
}

export default class BichosScreen extends React.Component<IProp> {
  public render() {
    return (
      <View style={{ flex: 1 }}>
        <StatusBar hidden={true} />
        <View style={{ flex: 15 }}>
          <Animated.ScrollView
            scrollEventThrottle={16}
            onScroll={Animated.event(
              [{ nativeEvent: { contentOffset: { x: xOffset } } }],
              { useNativeDriver: true }
            )}
            horizontal
            pagingEnabled
            showsHorizontalScrollIndicator={false}
            style={styles.scrollView}
          >
            <Animated.View style={transitionAnimation(0)}>
              <ShopScreen navigation={this.props.navigation} />
            </Animated.View>
            <Animated.View style={transitionAnimation(1)}>
              <InventoryScreen navigation={this.props.navigation} />
            </Animated.View>
            <Animated.View style={transitionAnimation(2)}>
              <BattleScreen navigation={this.props.navigation} />
            </Animated.View>
            <Animated.View style={transitionAnimation(3)}>
              <BichosInfoScreen navigation={this.props.navigation} />
            </Animated.View>
            <Animated.View style={transitionAnimation(4)}>
              <EggScreen navigation={this.props.navigation} />
            </Animated.View>
          </Animated.ScrollView>
        </View>
        <View style={{ flex: 2 }}>
          <BoottomMenu navigation={this.props.navigation} />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  scrollView: {
    flexDirection: "row",
    backgroundColor: "#00d4ff"
  }
});


