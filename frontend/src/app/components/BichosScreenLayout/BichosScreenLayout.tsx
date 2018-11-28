import React from "react";
import { View, Text, Button } from "react-native";
import styles from "./styles";
import ExpBar from "../ExpBar/ExpBar";
import AmountBar from "../AmountBar/AmountBar"
import SwordBar from "../SwordBar/SwordBar"
import axios from "axios";
import ITokenContextData from "../../models/ITokenContextData";
import TokenContext from "../../contexts/TokenContext";
import BugDisplay from "../../components/BugDisplay";
import { NavigationScreenProp } from "react-navigation";

interface IProps {
  lvl: number;
  exp: number;
  coins: number;
  swords: number;
  state: {
    bug: string,
  };
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class BichosScreenLayout extends React.Component<IProps> {

  public componentDidMount() {
    this.getBug();
  }

  private getBug() {
    axios
      .post("http://localhost:8080/bugs/generate")
      .then(response => {
        const bug = response.data;
        this.setState({ bug });
      });
  }
  public render() {
    return (
      <View style={{ flex: 1 }}>
        <View style={styles.head}>
          <View style={{ flex: 1 }}>
            <ExpBar lvl={this.props.lvl} exp={this.props.exp} />
          </View>
          <View style={{ flex: 1 }}>
            <AmountBar coins={this.props.coins} />
          </View>
          <View style={{ flex: 1 }}>
            <SwordBar swords={this.props.swords} />
          </View>
        </View>
        <View style={{ flex: 1, alignItems: "center", justifyContent: "center" }}>
          <Text>Bichos!</Text>
          <TokenContext.Consumer>
            {(contextData: ITokenContextData) => (
              <Button title="LOGOUT" onPress={() => contextData.logout()} />
            )}
          </TokenContext.Consumer>
          {this.props.state.bug ? (
            <BugDisplay bug={this.props.state.bug} width={400} />
          ) : null}
          <Button title="New Bug" onPress={() => this.getBug()} />
        </View>
      </View>
    );
  }
}
