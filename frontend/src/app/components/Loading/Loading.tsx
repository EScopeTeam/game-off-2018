import React from "react";
import { View, Text, Modal, ActivityIndicator } from "react-native";
import styles from "./styles";

export default class Loading extends React.Component {
  public render() {
    return (
      <Modal supportedOrientations={["portrait"]} transparent>
        <View style={styles.container}>
          <ActivityIndicator
            color="white"
            size="large"
            style={styles.activityIndicator}
          />
          <View style={styles.textContainer}>
            <Text style={styles.textContent}>Loading...</Text>
          </View>
        </View>
      </Modal>
    );
  }
}
