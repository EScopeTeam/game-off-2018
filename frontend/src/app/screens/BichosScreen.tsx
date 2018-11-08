import React from "react";
import { View, Text, Button } from "react-native";
import { IUserContextData } from "../models/IUserContextData";
import UserContext from "../contexts/UserContext";

export default class BichosScreen extends React.Component {
  public render() {
    return (
      <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
        <Text>Bichos!</Text>
        <UserContext.Consumer>
          {(contextData: IUserContextData) => (
            <Button title="LOGOUT" onPress={() => contextData.logout()} />
          )}
        </UserContext.Consumer>
      </View>
    );
  }
}
