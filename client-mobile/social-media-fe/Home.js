import React, { useState, useEffect } from 'react';
import { View, Text, FlatList, StyleSheet, Image, Alert } from 'react-native';
import Header from './Header ';
import API_URL from './Env';
import * as SecureStore from 'expo-secure-store';

const Home = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchProducts = async () => {
    try {
      const accesstoken = await SecureStore.getItemAsync('accesstoken');
      if (!accesstoken) {
        Alert.alert('Error', 'No access token found');
        return;
      }

      const response = await fetch(`${API_URL}/api/home/getAllProduct`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${accesstoken}`,
          'Content-Type': 'application/json',
        },
      });

      const responseData = await response.json();
      if (responseData.status === 200) {
        setProducts(responseData.data);
      } else {
        Alert.alert('Error', 'Failed to fetch products');
      }
    } catch (error) {
      Alert.alert('Error', 'An error occurred while fetching products');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  // Render each product item
  const renderItem = ({ item }) => (
    <View style={styles.productContainer}>
      <Image source={{ uri: item.productDetail.imageUrl }} style={styles.productImage} />
      <Text style={styles.productName}>{item.productDetail.name}</Text>
    </View>
  );

  return (
    <View style={styles.container}>
      <Header title="Main Screen" showBackButton={false} />
      {loading ? (
        <Text>Loading...</Text>
      ) : (
        <FlatList
          data={products}
          renderItem={renderItem}
          keyExtractor={(item) => item.productID.toString()}
          numColumns={2}
          contentContainerStyle={styles.productList}
        />
      )}
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
