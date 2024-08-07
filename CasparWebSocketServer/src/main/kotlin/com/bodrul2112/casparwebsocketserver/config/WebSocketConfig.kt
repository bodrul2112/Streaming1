package com.bodrul2112.casparwebsocketserver.config

import com.bodrul2112.casparwebsocketserver.handler.WebSocketMessageHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
@Configuration
@EnableWebSocket
class WebSocketConfig(private val messageHandler: WebSocketMessageHandler) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(messageHandler, "/ws/messages").setAllowedOrigins("*")
    }
}