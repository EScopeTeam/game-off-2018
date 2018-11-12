import React from "react";
import { View, ActivityIndicator, Text, Button } from "react-native";
import EventBusStatus from "../../models/EventBusStatus";
import styles from "./styles";
import WebsocketClient from "../../utils/WebsocketClient";

interface IProp {
  eventBusStatus: EventBusStatus;
  eventBus: WebsocketClient;
}

export default class Reconnecting extends React.Component<IProp> {
  public render() {
    if (this.props.eventBusStatus === EventBusStatus.CONNECTING) {
      return (
        <View style={styles.container}>
          <ActivityIndicator
            color="white"
            size="large"
            style={styles.activityIndicator}
          />
          <View style={styles.textContainer}>
            <Text style={styles.textContent}>Connecting...</Text>
          </View>
        </View>
      );
    } else {
      return (
        <View style={styles.container}>
          <View style={styles.textContainer}>
            <Text style={styles.textContent}>Disconnected</Text>
            <Button
              title="RECONNECT"
              onPress={() => this.props.eventBus.connect()}
            />
          </View>
        </View>
      );
    }
  }
}
