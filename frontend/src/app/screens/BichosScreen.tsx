import React from "react";
import axios from "axios";
import { View, Text, Button } from "react-native";
import ITokenContextData from "../models/ITokenContextData";
import TokenContext from "../contexts/TokenContext";
import BugDisplay from "../components/BugDisplay";
import IBug from "../models/IBug";

interface IState {
  bug: IBug | null;
}

export default class BichosScreen extends React.Component<{}, IState> {
  constructor(props: {}) {
    super(props);

    this.state = {
      bug: null,
    };
  }

  public componentDidMount() {
    this.getBug();
  }

  private getBug() {
    axios
      .post("http://localhost:8080/bugs/generate")
      .then(response => {
        const bug = response.data;
        this.setState({ bug });
      })
      .catch(error => console.error(error));
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
        {this.state.bug ? (
          <BugDisplay bug={this.state.bug} width={400} />
        ) : null}
        <Button title="New Bug" onPress={() => this.getBug()} />
      </View>
    );
  }
}
