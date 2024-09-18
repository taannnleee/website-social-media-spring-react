import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, TextInput } from 'react-native';
import Header from './Header ';  

const Profile = () => {
  const [isEditing, setIsEditing] = useState(true);
  const [profileData, setProfileData] = useState({
    fullname: 'LÊ TÂN',
    username: 'letan123',
    email: 'email@example.com',
    phone: '0123456789',
    city: 'Hanoi',
    stress: '123 Main St',
    state: 'VN'
  });

  const [updatedData, setUpdatedData] = useState({ ...profileData });

  return (
    <View style={styles.container}>
      <Header title="Profile" />
      
      <View style={styles.profileContainer}>
        <Text style={styles.label}>Full Name:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.fullname}
          onChangeText={(text) => setUpdatedData({ ...updatedData, fullname: text })}
          editable={isEditing}
        />

        <Text style={styles.label}>Username:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.username}
          onChangeText={(text) => setUpdatedData({ ...updatedData, username: text })}
          editable={isEditing}
        />

        <Text style={styles.label}>Email:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.email}
          onChangeText={(text) => setUpdatedData({ ...updatedData, email: text })}
          editable={isEditing}
        />

        <Text style={styles.label}>Phone:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.phone}
          onChangeText={(text) => setUpdatedData({ ...updatedData, phone: text })}
          editable={isEditing}
        />

        <Text style={styles.label}>City:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.city}
          onChangeText={(text) => setUpdatedData({ ...updatedData, city: text })}
          editable={isEditing}
        />

        <Text style={styles.label}>Street:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.stress}
          onChangeText={(text) => setUpdatedData({ ...updatedData, stress: text })}
          editable={isEditing}
        />

        <Text style={styles.label}>State:</Text>
        <TextInput
          style={styles.input}
          value={updatedData.state}
          onChangeText={(text) => setUpdatedData({ ...updatedData, state: text })}
          editable={isEditing}
        />

        <TouchableOpacity
          style={styles.button}
        >
          <Text style={styles.buttonText}>{isEditing ? 'Save' : 'Update'}</Text>
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
