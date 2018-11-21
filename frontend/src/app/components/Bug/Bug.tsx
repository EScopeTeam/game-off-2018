import React from "react";
import { Text } from "react-native";
import { Buffer } from "buffer";

interface IProp {
  bug: string;
}

export default class Bug extends React.Component<IProp> {
  public render() {
    const bug = new Buffer(this.props.bug, "base64").toString("ascii");

    return <Text>{bug}</Text>;
  }
}
