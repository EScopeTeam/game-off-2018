import React from "react";
import { View, TouchableOpacity, Image, Text } from "react-native";
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { t } from "../config/i18n";
import { navigationStyles } from "./../config/globalStyles";

interface IProp {
  readonly navigation: NavigationScreenProp<any, any>;
}

export default class LoginScreen extends React.Component {

  public static navigationOptions: NavigationScreenOptions = {
    header: null,
    ...navigationStyles,
  };

  public render() {
    return (
      
      <View style={{ flex: 1}}>
        <View style={{ flex: 1, flexDirection: "row", justifyContent: "space-around", marginTop: 5 }}>
          <View style={{ flex: 1 }}>
            <View style={{ borderColor: '#3db39e', backgroundColor: "#3db39e", borderWidth: 1, height: 20, position: 'relative', left: 26, top: 5, width: 90 }}>
              <View style={{ backgroundColor: '#138775', flex: 1, width: 50 }} >

              </View>
            </View>
            <Image source={require("../../../assets/bichosScreenIcons/new.png")} style={{ position: 'relative', left: 4, top: -20, width: 30, height: 30 }} />
            <Text style={{ color: '#fff', position: 'relative', top: -45, left: 14 }}>5</Text>
          </View>
          <View style={{ flex: 1 }}>
            <View style={{ borderColor: '#fea832', backgroundColor: "#fea832", borderWidth: 1, height: 20, position: 'relative', left: 15, top: 5, width: 90 }}>
              <Text style={{ color: '#fff', textAlign: "center" }}>555555</Text>
            </View>
            <TouchableOpacity>
              <Image source={require("../../../assets/bichosScreenIcons/anadir.png")} style={{ position: 'relative', left: 4, top: -15, width: 20, height: 20 }} />
            </TouchableOpacity>
            <Image source={require("../../../assets/bichosScreenIcons/dolar.png")} style={{ position: 'relative', left: 95, top: -38, width: 25, height: 25 }} />
          </View>
          <View style={{ flex: 1 }}>
            <View style={{ backgroundColor: '#b3b3b3', borderColor: '#b3b3b3', borderWidth: 1, height: 20, position: 'relative', left: 20, top: 5, width: 90 }}>
              <Text style={{ color: '#fff', textAlign: "center" }}>5</Text>
            </View>
            <Image source={require("../../../assets/bichosScreenIcons/sword.png")} style={{ position: 'relative', left: 4, top: -20, width: 30, height: 30 }} />
          </View>
        </View>
        <View style={{ flex: 15, }}>

        </View>
        <View style={{ flex: 2, backgroundColor: "#35464d", flexDirection: "row", justifyContent: "space-around" }}>
          <TouchableOpacity style={{ marginVertical: 8 }}>
            <Image source={require("../../../assets/bichosScreenIcons/stand.png")} style={{ width: 50, height: 50 }} />
          </TouchableOpacity>
          <View style={{ borderLeftWidth: 1, borderLeftColor: '#B6B6B5', shadowColor: "#646463", shadowOffset: { width: 0, height: 1, }, shadowOpacity: 0.22, shadowRadius: 2.22, elevation: 3, }} />
          <TouchableOpacity style={{ marginVertical: 8 }}>
            <Image source={require("../../../assets/bichosScreenIcons/backpack.png")} style={{ width: 50, height: 50 }} />
          </TouchableOpacity>
          <View style={{ borderLeftWidth: 1, borderLeftColor: '#B6B6B5', shadowColor: "#646463", shadowOffset: { width: 0, height: 1, }, shadowOpacity: 0.22, shadowRadius: 2.22, elevation: 3, }} />
          <TouchableOpacity style={{ position: 'relative', top: -20 }}>
            <Image source={require("../../../assets/bichosScreenIcons/swords.png")} style={{ width: 80, height: 80 }} />
          </TouchableOpacity>
          <View style={{ borderLeftWidth: 1, borderLeftColor: '#B6B6B5', shadowColor: "#646463", shadowOffset: { width: 0, height: 1, }, shadowOpacity: 0.22, shadowRadius: 2.22, elevation: 3, }} />
          <TouchableOpacity style={{ marginVertical: 8 }}>
            <Image source={require("../../../assets/bichosScreenIcons/monster.png")} style={{ width: 50, height: 50 }} />
          </TouchableOpacity>
          <View style={{ borderLeftWidth: 1, borderLeftColor: '#B6B6B5', shadowColor: "#646463", shadowOffset: { width: 0, height: 1, }, shadowOpacity: 0.22, shadowRadius: 2.22, elevation: 3, }} />
          <TouchableOpacity style={{ marginVertical: 8 }}>
            <Image source={require("../../../assets/bichosScreenIcons/easter.png")} style={{ width: 50, height: 50 }} />
          </TouchableOpacity>
        </View>
      </View>
    );
  }
}
