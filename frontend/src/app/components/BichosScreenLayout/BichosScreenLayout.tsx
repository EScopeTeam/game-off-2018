import React from "react";
import { View, Text, Button } from "react-native";
import styles from "./styles";
import ExpBar from "../ExpBar/ExpBar";
import AmountBar from "../AmountBar/AmountBar";
import SwordBar from "../SwordBar/SwordBar";
import axios from "axios";
import ITokenContextData from "../../models/ITokenContextData";
import TokenContext from "../../contexts/TokenContext";
import BugDisplay from "../../components/BugDisplay";

interface IState {
  lvl: number;
  exp: number;
  coins: number;
  swords: number;
  bug?: any;
}

export default class BichosScreenLayout extends React.Component<{}, IState> {
  constructor(props: {}) {
    super(props);

    this.state = { lvl: 5, exp: 2500, coins: 10000, swords: 1500 };
  }

  public componentDidMount() {
    this.getBug();
  }

  private getBug() {
    axios.post("http://localhost:8080/bugs/generate").then(response => {
      const bug = response.data;
      this.setState({ bug });
    });
  }
  public render() {
    return (
      <View style={{ flex: 1 }}>
        <View style={styles.head}>
          <View style={{ flex: 1 }}>
            <ExpBar lvl={this.state.lvl} exp={this.state.exp} />
          </View>
          <View style={{ flex: 1 }}>
            <AmountBar coins={this.state.coins} />
          </View>
          <View style={{ flex: 1 }}>
            <SwordBar swords={this.state.swords} />
          </View>
        </View>
        <View
          style={{ flex: 1, alignItems: "center", justifyContent: "center" }}
        >
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
      </View>
    );
  }
}
