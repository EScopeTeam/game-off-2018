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
            <View>
                <GenericTouchableOpacity source={require("../../../assets/bichosScreenIcons/stand.png")} onPress={() => navigation.navigate("Shop")}/>
                <VerticalLine />
                <GenericTouchableOpacity source={require("../../../assets/bichosScreenIcons/backpack.png")}/>
                <VerticalLine />
                <TouchableOpacity style={{ position: 'relative', top: -20 }}>
                    <Image source={require("../../../assets/bichosScreenIcons/swords.png")} style={{ width: 80, height: 80 }} />
                </TouchableOpacity>
                <VerticalLine />
                <GenericTouchableOpacity source={require("../../../assets/bichosScreenIcons/monster.png")}/>>
                <VerticalLine />
                <GenericTouchableOpacity source={require("../../../assets/bichosScreenIcons/easter.png")}/>>
            </View>
        );
    }
}