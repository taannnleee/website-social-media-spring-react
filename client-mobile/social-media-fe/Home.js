import React from 'react';
import { View, Text, FlatList, StyleSheet, Image } from 'react-native';
import Header from './Header ';  

// Dummy data for products
const products = [
  { id: '1', name: 'Product 1', image: 'https://via.placeholder.com/150' },
  { id: '2', name: 'Product 2', image: 'https://via.placeholder.com/150' },
  { id: '3', name: 'Product 3', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 4', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 5', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 6', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 7', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 8', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 9', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 10', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 11', image: 'https://via.placeholder.com/150' },
  { id: '4', name: 'Product 12', image: 'https://via.placeholder.com/150' }
];

const Home = () => {
  // Render each product item
  const renderItem = ({ item }) => (
    <View style={styles.productContainer}>
      <Image source={{ uri: item.image }} style={styles.productImage} />
      <Text style={styles.productName}>{item.name}</Text>
    </View>
  );

  return (
    <View style={styles.container}>
      <Header title="Main Screen" showBackButton={false} />

      <FlatList
        data={products}
        renderItem={renderItem}
        keyExtractor={(item) => item.id}
        numColumns={2}
        contentContainerStyle={styles.productList}
      />
    </View>
  );
};


const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
  },
  productList: {
    flexGrow: 1,
  },
  row: {
    justifyContent: 'space-between',
  },
  productContainer: {
    flex: 1,
    margin: 5,
    alignItems: 'center',
  },
  productImage: {
    width: '100%',
    height: 250,
    borderRadius: 10,
  },
  productName: {
    marginTop: 5,
    fontSize: 16,
    fontWeight: 'bold',
  },
});

export default Home;