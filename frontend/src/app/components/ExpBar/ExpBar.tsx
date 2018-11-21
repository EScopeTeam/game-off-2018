import React from "react";
import { View, Text, Image } from "react-native";
import styles from "./styles";

interface IProps {
  lvl: number;
  exp: number;
}

export default class ExpBar extends React.Component<IProps> {
  public render() {
    return (
      <View>
        <View style={styles.bar}>
          <View style={[styles.solidBar,{width: (this.props.lvl + 26)}]} />
        </View>
        <Image source={require("../../../assets/bichosScreenIcons/new.png")} style={styles.imageLvl} />
        <Text style={styles.textLvl}>{this.props.lvl}</Text>
      </View>
    );
  }
}
