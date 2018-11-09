import React from "react";
import FormFieldError from "../../models/FormFieldError";
import styles from "./styles";
import { View, Text } from "react-native";

interface IProps {
  errors?: FormFieldError[];
  lang: string;
}

export default class GenericTextInputErrors extends React.Component<IProps> {
  public render() {
    const errors = this.props.errors;

    if (errors && errors.length > 0) {
      return (
        <View style={styles.errorContainer}>
          {errors.map(error => (
            <Text style={styles.errorText}>
              {error.getMessage(this.props.lang)}
            </Text>
          ))}
        </View>
      );
    } else {
      return null;
    }
  }
}
