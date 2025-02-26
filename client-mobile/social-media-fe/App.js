import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert, StyleSheet } from 'react-native';
import LoginScreen from './LoginScreen';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import AboutMeScreen from './AboutMeScreen';
import RegisterScreen from './RegisterScreen';
import VerifyOTP from './VerifyOTP';
import Home from './Home';
import Profile from './Profile';
// import ForgotPassword from './ForgotPassword';


const Stack = createStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" component={LoginScreen} />
        <Stack.Screen name="RegisterScreen" component={RegisterScreen} />
        <Stack.Screen name="AboutMeScreen" component={AboutMeScreen} />
        <Stack.Screen name="VerifyOTP" component={VerifyOTP} />
        <Stack.Screen name="Home" component={Home} />
        <Stack.Screen name="Profile" component={Profile} />
        
        {/* <Stack.Screen name="ForgotPassword" component={ForgotPassword} /> */}
      </Stack.Navigator>
    </NavigationContainer>
  );
};


export default App;
