import React from "react";
import { Button, ButtonProps } from "react-native-elements";
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