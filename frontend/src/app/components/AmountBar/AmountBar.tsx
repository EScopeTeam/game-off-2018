import React from "react";
import { View, Text, Image, TouchableOpacity } from "react-native";
import styles from "./styles";

interface IProps {
  amount: number;
}

export default class AmountBar extends React.Component<IProps> {
  public render() {
    return (
      <View>
        <View style={styles.bar}>
          <Text style={styles.text}>{this.props.amount}}</Text>
        </View>
        <TouchableOpacity>
          <Image source={require("../../../assets/bichosScreenIcons/anadir.png")} style={styles.add} />
        </TouchableOpacity>
        <Image source={require("../../../assets/bichosScreenIcons/dolar.png")} style={styles.dolar} />
      </View>
    );
  }
}
