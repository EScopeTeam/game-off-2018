import React from "react";
import { TouchableOpacity, Image, ImageProps, StyleProp, ImageStyle, TouchableOpacityProps } from "react-native";
import styles from "./styles";

interface IProps extends ImageProps {
    imageStyle?: StyleProp<ImageStyle>;
    press: TouchableOpacityProps<onkeypress>;
}

export default class GenericTouchableOpacity extends React.Component<IProps> {
    public render() {
        return (
            <TouchableOpacity style={styles.touchable}>
                <Image
                    style={this.props.imageStyle ? (this.props.imageStyle) : styles.image}
                    {...this.props} />
            </TouchableOpacity>
        );
    }
}