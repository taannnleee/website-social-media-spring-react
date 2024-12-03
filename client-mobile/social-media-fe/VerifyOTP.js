import React, { useState } from 'react';
import { View, TouchableOpacity, Text, TextInput, StyleSheet, Alert } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import API_URL from './Env';

const VerifyOTP = () => {
  const [OTP, setOTP] = useState('');
  const [email, setEmail] = useState('');
  const navigation = useNavigation();

  const handleConfirm = async () => {
    // Tạo form data cho yêu cầu POST
    const formData = new URLSearchParams();
    formData.append('email', email);
    formData.append('OTP', OTP);

    try {
      const response = await fetch(`${API_URL}/api/auth/verifyOTP_register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: formData.toString(),
      });

      // Kiểm tra mã trạng thái phản hồi
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      // Kiểm tra loại dữ liệu phản hồi
      const contentType = response.headers.get('Content-Type');
      if (contentType && contentType.includes('application/json')) {
        const responseData = await response.json();
        if (responseData.status === 200) {
          Alert.alert('Success', responseData.message || 'OTP verification successful.');
          navigation.navigate('Login');
        } else {
          Alert.alert('Error', responseData.message);
        }
      } else {
        // Nếu phản hồi không phải JSON
        const text = await response.text();
        Alert.alert('Error', `Unexpected response format: ${text}`);
      }
      
    } catch (error) {
      Alert.alert('Error', 'An error occurred while verifying OTP. Please try again later.');
      console.error('Error:', error);
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.formContainer}>
        <Text style={styles.title}>Verify OTP</Text>
        <TextInput
          style={styles.input}
          placeholder="Email"
          placeholderTextColor="#aaa"
          value={email}
          onChangeText={setEmail}
        />
        <TextInput
          style={styles.input}
          placeholder="OTP"
          placeholderTextColor="#aaa"
          value={OTP}
          onChangeText={setOTP}
        />
        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.btn} onPress={handleConfirm}>
            <Text style={styles.btnText}>Confirm</Text>
          </TouchableOpacity>
        </View>
        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.btn} onPress={() => navigation.navigate('Login')}>
            <Text style={styles.btnText}>Back to Login</Text>
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

export default VerifyOTP;
