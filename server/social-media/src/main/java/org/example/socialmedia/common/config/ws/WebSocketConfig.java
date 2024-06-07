//package org.example.socialmedia.config;
//
//import org.example.socialmedia.ws.DataHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//    /**
//     * http:localhost:8080/data
//
//     */
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(getDataHandler(), "/data");
//
//    }
//    @Bean
//    DataHandler getDataHandler(){
//        return new DataHandler();
//    }
//}
