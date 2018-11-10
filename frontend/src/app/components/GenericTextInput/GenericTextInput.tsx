import React from "react";
import { View, TextInput, Text, TextInputProps } from "react-native";
import IFormField from "../../models/IFormField";
import IFormFieldValue from "../../models/IFormFieldValue";
import GenericTextInputErrors from "./GenericTextInputErrors";
import { t } from "../../config/i18n";
import styles from "./styles";

interface IProps extends TextInputProps {
  field: IFormField;
  fieldValue: IFormFieldValue;
}

export default class GenericTextInput extends React.Component<IProps> {
  public render() {
    const field: IFormField = this.props.field;
    const fieldValue: IFormFieldValue = this.props.fieldValue;

    return (
      <View style={styles.container}>
        {field.labelCode ? (
          <Text style={styles.label}>
            {t(field.labelCode, field.labelParams)}
          </Text>
        ) : null}
        <TextInput
          {...this.props}
          onChangeText={value => field.setter(value)}
          value={fieldValue.value}
          placeholder={
            field.placeholderCode
              ? t(field.placeholderCode, field.placeholderParams)
              : null
          }
          style={styles.input}
        />
        <GenericTextInputErrors errors={fieldValue.errors} />
      </View>
    );
  }
}
