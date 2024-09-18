import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import Header from './Header';  // Đường dẫn component Header
import Footer from './Footer';  // Đường dẫn component Footer

const Home = () => {
  return (
    <View style={styles.container}>
      <Header title="Main Screen" />
      
      <View style={styles.content}>
        <Text>Welcome to the main content area!</Text>
      </View>

      <Footer text="Footer content here" />
    </View>
  );
};

export default Home;