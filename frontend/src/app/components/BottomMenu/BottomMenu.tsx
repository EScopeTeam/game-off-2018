import React from "react";
import { View, TouchableOpacity, Image } from "react-native";
import { NavigationScreenProp } from "react-navigation";
import VerticalLine from "../VerticalLine/VerticalLine";
import GenericTouchableOpacity from "../GenericTouchableOpacity/GenericTouchableOpacity";


interface IProp {
    readonly navigation: NavigationScreenProp<any, any>;
}

export default class BottomMenu extends React.Component<IProp> {
    public render() {
        return (
            <View style={{ flex: 2, backgroundColor: "#35464d", flexDirection: "row", justifyContent: "space-around" }}>
                <GenericTouchableOpacity source={require("../../../../assets/bichosScreenIcons/stand.png")} onPress={() => this.props.navigation.navigate("Shop")} />
                <VerticalLine />
                <GenericTouchableOpacity source={require("../../../../assets/bichosScreenIcons/backpack.png")} onPress={() => this.props.navigation.navigate("Inventory")} />
                <VerticalLine />
                <TouchableOpacity style={{ position: 'relative', top: -20 }} onPress={() => this.props.navigation.navigate("Battle")}>
                    <Image source={require("../../../../assets/bichosScreenIcons/swords.png")} style={{ width: 80, height: 80 }} />
                </TouchableOpacity>
                <VerticalLine />
                <GenericTouchableOpacity source={require("../../../../assets/bichosScreenIcons/monster.png")} onPress={() => this.props.navigation.navigate("BichosInfo")} />
                <VerticalLine />
                <GenericTouchableOpacity source={require("../../../../assets/bichosScreenIcons/easter.png")} onPress={() => this.props.navigation.navigate("Egg")} />
            </View>
        );
    }
}