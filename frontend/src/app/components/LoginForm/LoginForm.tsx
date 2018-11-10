import React from "react";
import { NavigationScreenProp } from "react-navigation";
import { View, Text, Button } from "react-native";
import UserContext from "./../../contexts/UserContext";
import { authenticationClient, playersClient } from "../../config/clients";
import { IUserContextData } from "../../models/IUserContextData";
import { saveToken } from "../../utils/authenticationHelper";
import i18n from "../../config/i18n";
import styles from "./styles";
import IFormField from "../../models/IFormField";
import IFormFieldValue from "../../models/IFormFieldValue";
import GenericTextInput from "../GenericTextInput/GenericTextInput";
import validate from "validate.js";
import { loginConstraints } from "./loginConstraints";
import { setStateWithValidationErrors } from "../../utils/validationHelper";

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

interface IState {
  username: IFormFieldValue;
  password: IFormFieldValue;
}

export default class LoginForm extends React.Component<IProp, IState> {
  private _form: { [key: string]: IFormField };

  constructor(props: IProp) {
    super(props);

    this.state = {
      username: {
        value: "",
      },
      password: {
        value: "",
      },
    };

    this._form = {
      username: {
        name: "username",
        labelCode: "login:username",
        setter: (value: string) => this.setState({ username: { value } }),
      },
      password: {
        name: "password",
        labelCode: "login:password",
        setter: (value: string) => this.setState({ password: { value } }),
      },
    };
  }

  private submit(contextData: IUserContextData): void {
    const form = {
      username: this.state.username.value,
      password: this.state.password.value,
    };

    validate
      .async(form, loginConstraints)
      .then(() => {
        authenticationClient
          .login(form.username, form.password)
          .then((token: string) => {
            // TODO handle 401 error
            // TODO handle error in forms
            saveToken(token).then(() => {
              playersClient
                .getCurrentUser()
                .then(user => contextData.login(user));
            });
          });
      })
      .catch(error => {
        setStateWithValidationErrors(
          this._form,
          error,
          this.state,
          this.setState.bind(this)
        );
      });
  }

  public render() {
    const navigation = this.props.navigation;

    return (
      <View style={styles.container}>
        <Text>Login {i18n.language}</Text>
        <GenericTextInput
          field={this._form.username}
          fieldValue={this.state.username}
          keyboardType="email-address"
        />
        <GenericTextInput
          field={this._form.password}
          fieldValue={this.state.password}
          secureTextEntry={true}
        />
        <UserContext.Consumer>
          {(contextData: IUserContextData) => (
            <Button title="LOGIN" onPress={() => this.submit(contextData)} />
          )}
        </UserContext.Consumer>
        <Button title="SIGNUP" onPress={() => navigation.navigate("Signup")} />
      </View>
    );
  }
}
