import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const Footer = ({ text }) => {
  return (
    <View style={styles.footer}>
      <Text style={styles.footerText}>{text}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  footer: {
    height: 50,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f8f8f8',
    borderTopWidth: 1,
    borderTopColor: '#ddd',
  },
  footerText: {
    color: '#333',
    fontSize: 16,
  },
});

export default Footer;
