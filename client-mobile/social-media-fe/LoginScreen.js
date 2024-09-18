import React, { useState } from 'react';
import { View, TouchableOpacity, Text, TextInput, StyleSheet, Alert } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import * as SecureStore from 'expo-secure-store'; 
import API_URL from './Env';

const LoginScreen = () => {
  const [username, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const navigation = useNavigation();

  const handleLogin = async () => {
    const loginRequest = {
        username: username,
        password: password,
    };

    try {
      
      const response = await fetch(`${API_URL}/api/auth/login`, { 
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginRequest),
      });

      const responseData = await response.json();
      if (responseData.status === 200) {
        const {userid}  = responseData.data;
        const useridString  = String(userid);
        await SecureStore.setItemAsync('user_id', useridString);

        const user_id = await SecureStore.getItemAsync('user_id');

  
        Alert.alert('Success',  'Login successful');
        navigation.navigate('Home');
     
      } else {
        if(responseData.message ==="Accout Is Not Active"){
          Alert.alert('Error',  'Accout is not active'); 
          navigation.navigate('VerifyOTP');

        }else{
          Alert.alert('Error',  'Username or password is incorrect.'); 
        }
        
      }
      
    } catch (error) {
        Alert.alert('Error',  error);
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.formContainer}>
        <Text style={styles.title}>Sign In</Text>
        <TextInput
          style={styles.input}
          placeholder="UserName"
          placeholderTextColor="#aaa"
          value={username}
          onChangeText={setUserName}
        />
        <TextInput
          style={styles.input}
          placeholder="Password"
          secureTextEntry
          placeholderTextColor="#aaa"
          value={password}
          onChangeText={setPassword}
        />

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.btn} onPress={handleLogin}>
            <Text style={styles.btnText}>Login</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.buttonContainer}>
        <TouchableOpacity style={styles.btn} onPress={() => navigation.navigate('ForgotPassword')}>
            <Text style={styles.btnText}>Forget Password</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.btn} onPress={() => navigation.navigate('RegisterScreen')}>
            <Text style={styles.btnText}>Register</Text>
          </TouchableOpacity>
        </View>
      </View>


    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#fff',
  },
  formContainer: {
    width: '90%',
    alignItems: 'center',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  input: {
    width: '100%',
    height: 50,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 25,
    paddingHorizontal: 15,
    marginBottom: 20,
    backgroundColor: '#f0f0f0',
  },
  btn: {
    width: 350,
    height: 50,
    backgroundColor: '#5995fd',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 25,
  },
  btnText: {
    color: '#fff',
    fontWeight: '600',
  },
  buttonContainer: {
    marginVertical: 10,
  },
});

export default LoginScreen;
