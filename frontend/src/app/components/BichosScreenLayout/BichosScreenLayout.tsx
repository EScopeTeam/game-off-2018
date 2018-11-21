import React from "react";
import { View, StatusBar } from "react-native";
import styles from "./styles";
import BoottomMenu from "../BottomMenu/BottomMenu";
import ExpBar from "../ExpBar/ExpBar";
import AmountBar from "../AmountBar/AmountBar"
import SwordBar from "../SwordBar/SwordBar"

interface IProps {
  lvl: number;
  exp: number;
  amount: number;
  swords: number;
}

export default class BichosScreenLayout extends React.Component<IProps> {
  public render() {
    return (
      <View style={{ flex: 1 }}>
        <StatusBar hidden={true} />
        <View style={styles.head}>
          <View style={{ flex: 1 }}>
            <ExpBar lvl={this.props.lvl} exp={this.props.exp}/>
          </View>
          <View style={{ flex: 1 }}>
            <AmountBar amount={this.props.amount}/>
          </View>
          <View style={{ flex: 1 }}>
            <SwordBar swords={this.props.swords}/>
          </View>
        </View>
        <View style={styles.body} />
        <View style={styles.foot}>
          <BoottomMenu />
        </View>
      </View>
    );
  }
}
