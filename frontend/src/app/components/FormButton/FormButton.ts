import React from "react";
import { Button, ButtonProps } from "react-native-elements";
import { t } from "../../config/i18n";
import styles from "./styles";

interface IProps extends ButtonProps {
}

export default class FormButton extends React.Component<IProps> {
    public render() {
        return (
            <Button 
                buttonStyle = { styles.button }
                {...this.props}
            />
        );
    }
}