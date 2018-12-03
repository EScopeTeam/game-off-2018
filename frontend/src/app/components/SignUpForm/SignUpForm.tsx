import React, { createRef } from "react";
import { AxiosError } from "axios";
import { NavigationScreenProp } from "react-navigation";
import { View, TextInput, Image } from "react-native";
import { Card } from "react-native-elements";
import TokenContext from "../../contexts/TokenContext";
import { authenticationClient } from "../../config/clients";
import ITokenContextData from "../../models/ITokenContextData";
import { saveToken } from "../../utils/authenticationHelper";
import styles from "./styles";
import IFormField from "../../models/IFormField";
import IFormFieldValue from "../../models/IFormFieldValue";
import GenericTextInput from "../GenericTextInput/GenericTextInput";
import FormButton from "../FormButton/FormButton";
import { signUpConstraints } from "./signUpConstraints";
import {
  getFieldValuesWithValidationErrors,
  getFieldValuesWithHttpErrors,
  validate,
} from "../../utils/validationHelper";
import ValidationError from "../../errors/ValidationError";
import IFormFieldError from "../../models/IFormFieldError";
import FormError from "../FormError";
import Loading from "../Loading";
import ISignUpForm from "../../models/ISignUpForm";

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
  readonly tokenContextData: ITokenContextData;
}

interface IState {
  email: IFormFieldValue;
  username: IFormFieldValue;
  password: IFormFieldValue;
  rePassword: IFormFieldValue;
  generalErrors: IFormFieldError[];
  loading: boolean;
}

class SignInForm extends React.Component<IProp, IState> {
  private _form: { [key: string]: IFormField };

  private usernameInput: React.RefObject<TextInput> = createRef<TextInput>();
  private passwordInput: React.RefObject<TextInput> = createRef<TextInput>();
  private rePasswordInput: React.RefObject<TextInput> = createRef<TextInput>();

  constructor(props: IProp) {
    super(props);

    this.state = {
      email: {
        value: "",
      },
      username: {
        value: "",
      },
      password: {
        value: "",
      },
      rePassword: {
        value: "",
      },
      generalErrors: [],
      loading: false,
    };

    this._form = {
      email: {
        name: "email",
        placeholderCode: "signup:email",
        setter: (value: string) => this.setState({ email: { value } }),
      },
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
      rePassword: {
        name: "rePassword",
        placeholderCode: "login:re-password",
        setter: (value: string) => this.setState({ rePassword: { value } }),
      },
    };
  }

  private submit(): void {
    const form: ISignUpForm = {
      username: this.state.username.value,
      password: this.state.password.value,
      email: this.state.email.value,
    };

    this.setState({
      generalErrors: [],
      username: { value: form.username },
      password: { value: form.password },
      email: { value: form.email },
      rePassword: { value: this.state.rePassword.value },
      loading: true,
    });

    if (form.password === this.state.rePassword.value) {
      validate(form, signUpConstraints)
        .then(() => {
          authenticationClient
            .signUp(form)
            .then(() => {
              this.setState({ loading: false }, () => {
                this.props.navigation.navigate("Login");
              });
            })
            .catch(this.setHttpErrors.bind(this));
        })
        .catch(this.setValidationErrors.bind(this));
    } else {
      this.setValidationPasswordErrors();
    }
  }

  private setGeneralError(error: Error): void {
    this.setState({
      loading: false,
      generalErrors: [{ code: "generalError" }],
    });
  }
  private setValidationPasswordErrors(): void {
    this.setState({
      loading: false,
      rePassword: {
        value: this.state.rePassword.value,
        errors: [{ code: "The repeated password is different" }],
      },
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
    } else if (error.response && error.request.status === 409) {
      newState = this.state;
      newState.username = {
        value: newState.username.value,
        errors: [{ code: "notUnique" }],
      };
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
        <Card containerStyle={{ height: 280 }}>
          <FormError errors={this.state.generalErrors} />
          <GenericTextInput
            field={this._form.email}
            fieldValue={this.state.email}
            keyboardType="email-address"
            onSubmitEditing={() => {
              if (this.usernameInput.current) {
                this.usernameInput.current.focus();
              }
            }}
          />
          <GenericTextInput
            field={this._form.username}
            fieldValue={this.state.username}
            keyboardType="email-address"
            onSubmitEditing={() => {
              if (this.passwordInput.current) {
                this.passwordInput.current.focus();
              }
            }}
            refInput={this.usernameInput}
          />
          <GenericTextInput
            field={this._form.password}
            fieldValue={this.state.password}
            secureTextEntry
            onSubmitEditing={() => {
              if (this.rePasswordInput.current) {
                this.rePasswordInput.current.focus();
              }
            }}
            refInput={this.passwordInput}
          />
          <GenericTextInput
            field={this._form.rePassword}
            fieldValue={this.state.rePassword}
            secureTextEntry
            onSubmitEditing={() => this.submit()}
            refInput={this.rePasswordInput}
          />
          <View style={styles.button_login}>
            <FormButton
              title="LOGIN"
              onPress={() => navigation.navigate("Login")}
            />
            <FormButton title="SIGNUP" onPress={() => this.submit()} />
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
        <SignInForm
          navigation={props.navigation}
          tokenContextData={tokenContextData}
        />
      )}
    </TokenContext.Consumer>
  );
};
