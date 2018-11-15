import React from "react";
import { Button, ButtonProps } from "react-native-elements";
import { t } from "../../config/i18n";
import styles from "./styles";

export default class FormButton extends React.Component<ButtonProps> {
    public render() {
        return (
            <Button
                buttonStyle={styles.button}
                {...this.props}
            />
        );
    }
}