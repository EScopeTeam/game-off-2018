import React from "react";
import { View } from "react-native";
import styles from "./styles";

export default class VerticalLine extends React.Component {
    public render() {
        return (
            <View style={styles.line} />
        );
    }
}