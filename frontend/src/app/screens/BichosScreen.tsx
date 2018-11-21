import React from "react";
import BichosScreenLayout from "./../components/BichosScreenLayout/BichosScreenLayout"

export default class LoginScreen extends React.Component {
  public render() {
    return <BichosScreenLayout lvl={5} exp={1000} amount={25000} swords={2500}/>;
  }
}
