import React from "react";
import { View, Text, Image } from "react-native";
import styles from "./styles";

interface IProps {
  swords: number;
}

export default class SwordBar extends React.Component<IProps> {
  public render() {
    return (
      <View>
        <View style={styles.bar}>
          <Text style={styles.text}>{this.props.swords}}</Text>
        </View>
        <Image source={require("../../../assets/bichosScreenIcons/sword.png")} style={styles.image} />
      </View>
    );
  }
}
