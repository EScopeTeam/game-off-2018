import React from "react";
import { View, TextInput, Text } from "react-native";
import IFormField from "../../models/IFormField";
import GenericTextInputErrors from "./GenericTextInputErrors";
import styles from "./styles";

interface IProps {
  field: IFormField;
  lang: string;
}

export default class GenericTextInput extends React.Component<IProps> {
  public render() {
    const field: IFormField = this.props.field;
    const lang: string = this.props.lang;

    return (
      <View style={styles.container}>
        {field.labelCode ? (
          <Text style={styles.label}>{field.labelCode}</Text>
        ) : null}
        <TextInput
          onChangeText={value => field.setter(value)}
          value={field.value}
          placeholder={field.placeholderCode}
          style={styles.input}
        />
        <GenericTextInputErrors errors={field.errors} lang={lang} />
      </View>
    );
  }
}
