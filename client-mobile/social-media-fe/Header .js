import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';

const Header = ({ title }) => {
  const navigation = useNavigation();

  return (
    <View style={styles.headerContainer}>
      {/* Nút Home */}
      <TouchableOpacity onPress={() => navigation.navigate('Home')} style={styles.iconButton}>
        <Text style={styles.iconText}>🏠</Text>
      </TouchableOpacity>

      {/* Nút Thông báo */}
      <TouchableOpacity onPress={() => navigation.navigate('Notifications')} style={styles.iconButton}>
        <Text style={styles.iconText}>🔔</Text>
      </TouchableOpacity>

      {/* Nút Tin nhắn */}
      <TouchableOpacity onPress={() => navigation.navigate('Messages')} style={styles.iconButton}>
        <Text style={styles.iconText}>💬</Text>
      </TouchableOpacity>

      {/* Nút Profile */}
      <TouchableOpacity onPress={() => navigation.navigate('Profile')} style={styles.iconButton}>
        <Text style={styles.iconText}>👤</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  headerContainer: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
    backgroundColor: '#5995fd',
    paddingVertical: 15,
    borderBottomWidth: 1,
    borderBottomColor: '#ddd',
  },
  iconButton: {
    padding: 2,
  },
  iconText: {
    fontSize: 40, 
    color: '#fff',
  },
});

export default Header;
