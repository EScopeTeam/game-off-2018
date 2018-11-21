import React from "react";
import { TouchableOpacity, Image, ImageProps, StyleProp, ImageStyle, GestureResponderEvent } from "react-native";
import styles from "./styles";

interface IProps extends ImageProps {
    imageStyle?: StyleProp<ImageStyle>;
    onPress: (event: GestureResponderEvent) => void;
}

export default class GenericTouchableOpacity extends React.Component<IProps> {
    public render() {
        return (
            <TouchableOpacity style={styles.touchable} onPress={this.props.onPress}> 
                <Image
                    style={this.props.imageStyle ? (this.props.imageStyle) : styles.image}
                    {...this.props} />
            </TouchableOpacity>
        );
    }
}