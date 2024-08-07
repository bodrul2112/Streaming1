package com.bodrul2112.casparwebsocketserver.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
data class Message(val type: String, val content: String)

@Component
class WebSocketMessageHandler(private val objectMapper: ObjectMapper) : TextWebSocketHandler() {

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val receivedMessage = objectMapper.readValue(message.payload, Message::class.java)
        println("Received: $receivedMessage")

        // Echo the message back
        val responseMessage = Message("response", "Received your message: ${receivedMessage.content}")
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(responseMessage)))
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("Connected: ${session.id}")
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: org.springframework.web.socket.CloseStatus) {
        println("Disconnected: ${session.id}")
    }
}