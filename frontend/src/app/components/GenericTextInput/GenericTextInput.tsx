import React from "react";
import { View, TextInput, Text, TextInputProps } from "react-native";
import IFormField from "../../models/IFormField";
import IFormFieldValue from "../../models/IFormFieldValue";
import FormError from "../FormError";
import { t } from "../../config/i18n";
import styles from "./styles";

interface IProps extends TextInputProps {
  field: IFormField;
  fieldValue: IFormFieldValue;
  refInput?: React.RefObject<TextInput>;
  keyLabel?: string;
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
          ref={this.props.refInput}
          autoCapitalize="none"
          keyboardAppearance="dark"
          returnKeyLabel={this.props.keyLabel}
          returnKeyType="next"
          underlineColorAndroid="transparent"
          clearButtonMode={"while-editing"}
          autoCorrect={false}
          spellCheck={false}
          placeholderTextColor="rgba(0,0,0,0.5)"
        />
        <FormError errors={fieldValue.errors} />
      </View>
    );
  }
}
