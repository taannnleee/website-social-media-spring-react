import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, TextInput, Alert } from 'react-native';
import Header from './Header ';
import * as SecureStore from 'expo-secure-store';
import API_URL from './Env';

const Profile = () => {
  const [profileData, setProfileData] = useState({
    fullname: '',
    username: '',
    email: '',
    phone: '',
    city: '',
    street: '',
    state: ''
  });
  const [updatedData, setUpdatedData] = useState({ ...profileData });

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const userId = await SecureStore.getItemAsync('user_id');
        const accesstoken = await SecureStore.getItemAsync('accesstoken');
        const response = await fetch(`${API_URL}/api/user/getProfile/${userId}`, {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${accesstoken}`,
            'Content-Type': 'application/json',
          },
        });

        if (response.ok) {
          const responseText = await response.text();
          if (responseText) {
            const responseData = JSON.parse(responseText);
            if (responseData.status === 200) {
              setProfileData(responseData.data);
              setUpdatedData(responseData.data);
            } else {
              Alert.alert('Error', responseData.message || 'Failed to fetch profile data.');
            }
          } else {
            Alert.alert('Error', 'Empty response from server.');
          }
        } else {
          Alert.alert('Error', 'Network response was not ok.');
        }
      } catch (error) {
        Alert.alert('Error', 'An error occurred while fetching profile data.');
        console.error('Error:', error);
      }
    };

    fetchProfile();
  }, []);

  const handleSave = async () => {
    try {
      const userId = await SecureStore.getItemAsync('user_id');
      const accessToken = await SecureStore.getItemAsync('accesstoken');
      const response = await fetch(`${API_URL}/api/user/updateProfile/${userId}`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${accessToken}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ ...updatedData, id: userId }),
      });

      if (response.ok) {
        const responseText = await response.text();
        if (responseText) {
          const responseData = JSON.parse(responseText);
          if (responseData.status === 200) {
            Alert.alert('Success', 'Profile updated successfully.');
            setProfileData(updatedData);
          } else {
            Alert.alert('Error', responseData.message || 'Failed to update profile.');
          }
        } else {
          Alert.alert('Error', 'Empty response from server.');
        }
      } else {
        Alert.alert('Error', 'Network response was not ok.');
      }
    } catch (error) {
      Alert.alert('Error', 'An error occurred while updating profile.');
      console.error('Error:', error);
    }
  };

  return (
    <View style={styles.container}>
      <Header title="Profile" />
      <View style={styles.profileContainer}>
        <Text style={styles.label}>Full Name:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.fullname}
          onChangeText={(text) => setUpdatedData({ ...updatedData, fullname: text })}
        />

        <Text style={styles.label}>Username:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.username}
          onChangeText={(text) => setUpdatedData({ ...updatedData, username: text })}
        />

        <Text style={styles.label}>Email:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.email}
          onChangeText={(text) => setUpdatedData({ ...updatedData, email: text })}
        />

        <Text style={styles.label}>Phone:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.phone}
          onChangeText={(text) => setUpdatedData({ ...updatedData, phone: text })}
        />

        <Text style={styles.label}>City:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.city}
          onChangeText={(text) => setUpdatedData({ ...updatedData, city: text })}
        />

        <Text style={styles.label}>Street:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.street}
          onChangeText={(text) => setUpdatedData({ ...updatedData, street: text })}
        />

        <Text style={styles.label}>State:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.state}
          onChangeText={(text) => setUpdatedData({ ...updatedData, state: text })}
        />

        <TouchableOpacity onPress={handleSave}
          style={styles.button}
        >
          <Text style={styles.buttonText}>Save</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  profileContainer: {
    marginTop: 30,
  },
  label: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  input: {
    fontSize: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
    marginBottom: 20,
    padding: 5,
    backgroundColor: '#f5f5f5',
    borderRadius: 5,
  },
  button: {
    backgroundColor: '#5995fd',
    padding: 15,
    borderRadius: 10,
    alignItems: 'center',
    marginTop: 20,
  },
  buttonText: {
    color: '#fff',
    fontSize: 18,
  },
});

export default Profile;
