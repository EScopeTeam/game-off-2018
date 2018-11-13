import React, { createRef } from "react";
import { AxiosError } from "axios";
import { NavigationScreenProp } from "react-navigation";
import { View, TextInput, Image } from "react-native";
import { Card, Button } from "react-native-elements";
import TokenContext from "../../contexts/TokenContext";
import { authenticationClient } from "../../config/clients";
import ITokenContextData from "../../models/ITokenContextData";
import { saveToken } from "../../utils/authenticationHelper";
import i18n from "../../config/i18n";
import styles from "./styles";
import IFormField from "../../models/IFormField";
import IFormFieldValue from "../../models/IFormFieldValue";
import GenericTextInput from "../GenericTextInput/GenericTextInput";
import { loginConstraints } from "./loginConstraints";
import {
  getFieldValuesWithValidationErrors,
  getFieldValuesWithHttpErrors,
  validate,
} from "../../utils/validationHelper";
import ValidationError from "../../errors/ValidationError";
import IFormFieldError from "../../models/IFormFieldError";
import FormError from "../FormError";
import Loading from "../Loading";

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
  readonly tokenContextData: ITokenContextData;
}

interface IState {
  username: IFormFieldValue;
  password: IFormFieldValue;
  generalErrors: IFormFieldError[];
  loading: boolean;
}

class LoginForm extends React.Component<IProp, IState> {
  private _form: { [key: string]: IFormField };

  private secondInput: React.RefObject<TextInput> = createRef<TextInput>();

  constructor(props: IProp) {
    super(props);

    this.state = {
      username: {
        value: "",
      },
      password: {
        value: "",
      },
      generalErrors: [],
      loading: false,
    };

    this._form = {
      username: {
        name: "username",
        placeholderCode: "login:username",
        setter: (value: string) => this.setState({ username: { value } }),
      },
      password: {
        name: "password",
        placeholderCode: "login:password",
        setter: (value: string) => this.setState({ password: { value } }),
      },
    };
  }

  private submit(): void {
    const form = {
      username: this.state.username.value,
      password: this.state.password.value,
    };

    this.setState({
      generalErrors: [],
      username: { value: form.username },
      password: { value: form.password },
      loading: true,
    });

    validate(form, loginConstraints)
      .then(() => {
        authenticationClient
          .login(form.username, form.password)
          .then((token: string) => {
            saveToken(token)
              .then(() => {
                this.setState({ loading: false }, () => {
                  this.props.tokenContextData.login(token);
                });
              })
              .catch(this.setGeneralError.bind(this));
          })
          .catch(this.setHttpErrors.bind(this));
      })
      .catch(this.setValidationErrors.bind(this));
  }

  private setGeneralError(): void {
    this.setState({
      loading: false,
      generalErrors: [{ code: "generalError" }],
    });
  }

  private setHttpErrors(error: AxiosError): void {
    let newState: IState;

    if (error.response && error.response.status === 401) {
      newState = this.state;
      newState.generalErrors = [
        {
          code: "wrongPasswordOrUsername",
        },
      ];
    } else if (error.response && error.request.status === 422) {
      newState = getFieldValuesWithHttpErrors(this._form, error, this.state);
    } else {
      newState = this.state;
      newState.generalErrors = [
        {
          code: "generalError",
        },
      ];
    }

    newState.loading = false;
    this.setState(newState);
  }

  private setValidationErrors(error: ValidationError): void {
    const newStatus: IState = getFieldValuesWithValidationErrors(
      this._form,
      error,
      this.state
    );
    newStatus.loading = false;

    this.setState(newStatus);
  }

  public render() {
    const navigation = this.props.navigation;

    return (
      <View style={styles.body_login}>
        {this.state.loading ? <Loading /> : null}
        <View style={styles.logo}>
          <Image source={require("../../../../assets/background_login.gif")} />
        </View>
        <Card containerStyle={{ height: 225 }}>
          <FormError errors={this.state.generalErrors} />
          <GenericTextInput
            field={this._form.username}
            fieldValue={this.state.username}
            keyboardType="email-address"
            autoCapitalize="none"
            keyboardAppearance="dark"
            returnKeyLabel="Definir"
            returnKeyType="next"
            underlineColorAndroid="transparent"
            clearButtonMode={"while-editing"}
            autoCorrect={false}
            spellCheck={false}
            placeholderTextColor="rgba(0,0,0,0.5)"
            onSubmitEditing={() => {
              if (this.secondInput.current) {
                this.secondInput.current.focus();
              }
            }}
          />
          <GenericTextInput
            field={this._form.password}
            fieldValue={this.state.password}
            autoCapitalize="none"
            keyboardAppearance="dark"
            returnKeyLabel="Definir"
            returnKeyType="go"
            underlineColorAndroid="transparent"
            autoCorrect={false}
            spellCheck={false}
            placeholderTextColor="rgba(0,0,0,0.5)"
            secureTextEntry
            clearButtonMode={"while-editing"}
            refInput={this.secondInput}
          />
          <View
            style={{
              flexDirection: "row",
              justifyContent: "space-around",
            }}
          >
            <Button
              title="LOGIN"
              onPress={() => this.submit()}
              backgroundColor="#389798"
              buttonStyle={{ marginTop: 20, width: 120 }}
            />
            <Button
              title="SIGNUP"
              onPress={() => navigation.navigate("Signup")}
              backgroundColor="#389798"
              buttonStyle={{ marginTop: 20, width: 120 }}
            />
          </View>
        </Card>
        <View style={styles.footer_login} />
      </View>
    );
  }
}

export default (props: any) => {
  return (
    <TokenContext.Consumer>
      {(tokenContextData: ITokenContextData) => (
        <LoginForm
          navigation={props.navigation}
          tokenContextData={tokenContextData}
        />
      )}
    </TokenContext.Consumer>
  );
};

