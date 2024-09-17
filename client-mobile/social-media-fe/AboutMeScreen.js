import React from 'react';
import { View, Text, Image, StyleSheet, ScrollView } from 'react-native';

const AboutMeScreen = () => {
    return (
        <ScrollView contentContainerStyle={styles.container}>
            <View style={styles.innerContainer}>
                <Image 
                    source={{ uri: 'https://scontent.fsgn2-9.fna.fbcdn.net/v/t39.30808-6/313872028_1714879798899320_5452127081679419373_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=6ee11a&_nc_eui2=AeGgS_T9wzY_lRj0nILuzNqi63fuIg83gqXrd-4iDzeCpQR8DLD8OXf3BejIfSJHmuSQDbHf3NYvz8WYyACdfFNL&_nc_ohc=Gp8Y9usR7XEQ7kNvgFSyOXE&_nc_ht=scontent.fsgn2-9.fna&oh=00_AYAXTzM_mlxpN2alpvUUxoMY4jI6uB4Y3dj86fYax66LSg&oe=66D87F05' }} 
                    style={styles.avatar}
                />
                <Text style={styles.header}>Thông tin cá nhân</Text>
                <Text style={styles.text}><Text style={styles.bold}>Tên:</Text> Lê Tân</Text>
                <Text style={styles.text}><Text style={styles.bold}>Tuổi:</Text> 21</Text>
                <Text style={styles.text}><Text style={styles.bold}>Nghề nghiệp:</Text> Sinh viên</Text>
                <Text style={styles.text}><Text style={styles.bold}>Địa chỉ:</Text> Đường số 11, Bình Thọ, Võ Văn Ngân, Thủ Đức, Thành phố Hồ Chí Minh</Text>
            </View>
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        padding: 20,
        alignItems: 'center',
    },
    innerContainer: {
        alignItems: 'center',
        maxWidth: 400,
        textAlign: 'center',
    },
    avatar: {
        borderRadius: 75,
        width: 150,
        height: 150,
        resizeMode: 'cover',
        marginBottom: 16,
    },
    header: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 10,
    },
    text: {
        fontSize: 16,
        marginBottom: 20,
        textAlign: 'justify',
    },
    bold: {
        fontWeight: 'bold',
    },
});

export default AboutMeScreen;
