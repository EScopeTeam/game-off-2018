import React from "react";
import IFormFieldError from "../../models/IFormFieldError";
import { View, Text } from "react-native";
import { t } from "../../config/i18n";
import styles from "./styles";

interface IProps {
  errors?: IFormFieldError[];
}

export default class GenericTextInputErrors extends React.Component<IProps> {
  private translateError(error: IFormFieldError): string {
    if (error.code) {
      const key = "formErrors:" + error.code;
      const result = t(key, error.params);
      if (result === key || result === error.code) {
        return error.description;
      } else {
        return result;
      }
    } else {
      return error.description;
    }
  }

  public render() {
    const errors = this.props.errors;

    if (errors && errors.length > 0) {
      return (
        <View style={styles.errorContainer}>
          {errors.map((error, index) => (
            <Text key={index} style={styles.errorText}>
              {this.translateError(error)}
            </Text>
          ))}
        </View>
      );
    } else {
      return null;
    }
  }
}
