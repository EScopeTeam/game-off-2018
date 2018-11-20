import React from "react";
import axios from "axios";
import { View, Text, Button } from "react-native";
import ITokenContextData from "../models/ITokenContextData";
import TokenContext from "../contexts/TokenContext";

export default class BichosScreen extends React.Component {
  public componentDidMount(): void {
    axios
      .post("http://10.226.14.52:8080/bugs/generate")
      .then(response => {
        const bug = response.data;
        console.log(bug);
      })
      .catch(error => console.log(error));
  }

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
