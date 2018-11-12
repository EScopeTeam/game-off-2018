import React from "react";
import { View, Text, Button } from "react-native";
import ITokenContextData from "../models/ITokenContextData";
import TokenContext from "../contexts/TokenContext";

export default class BichosScreen extends React.Component {
  public render() {
    return (
      <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
        <Text>Bichos!</Text>
        <TokenContext.Consumer>
          {(contextData: ITokenContextData) => (
            <Button title="LOGOUT" onPress={() => contextData.logout()} />
          )}
        </TokenContext.Consumer>
      </View>
    );
  }
}
