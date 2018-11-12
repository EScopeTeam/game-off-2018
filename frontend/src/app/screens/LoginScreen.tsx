import React from "react";
import {
  Text,
  View,
  StyleSheet,
  TextInput,
  Image,
  ImageBackground,
} from 'react-native';
import { Card, Button, Divider } from 'react-native-elements';
import {
  NavigationScreenProp,
  NavigationScreenOptions,
} from "react-navigation";
import { navigationStyles } from "./../config/globalStyles";


interface IProp {
  navigation: NavigationScreenProp<any, any>;
}

export default class LoginScreen extends React.Component<IProp, {}> {
  public static navigationOptions: NavigationScreenOptions = {
    title: "Login",
    ...navigationStyles,
  };

  public render() {
    const navigation = this.props.navigation;

    return (
      <View style={styles.body}>
        <ImageBackground
          source={require('./assets/Rain.png')}
          style={{ width: '100%', height: '100%' }}
          resizeMode="repeat">
          <Image style={styles.logo} />
          <Card>
            <TextInput defaultValue="Insert your user" />

            <Divider style={{ backgroundColor: 'blue' }} />

            <TextInput defaultValue="Insert your password" />
          </Card>
          <View style={styles.footer}>
            <View
              style={{
                flexDirection: 'row',
                justifyContent: 'space-around',
              }}>
              <Card flexDirection= "row" containerStyle={{ padding: 0 }}>
                <Button large style={{margin:0}} title="Sign In" backgroundColor="#389798" />
              </Card>
              <Button large title="Sign Up" backgroundColor="#389798" onPress={() => navigation.navigate("Signup")} />
              
            </View>
            <View
              style={{
                flex: 3,
              }}
            />
          </View>
        </ImageBackground>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  logo: {
    flex: 2,
    justifyContent: 'center',
    padding: 8,
  },
  body: {
    flex: 1,
    backgroundColor: '#4cd4cc',
  },
  footer: {
    flex: 2,
  },
});
