package com.chatbot.chatBot.DTO;

import java.sql.Timestamp;
import java.util.UUID;

public class ChatResponseDTO {

    private UUID messageId;
    private String sender;
    private Timestamp timestamp;
    private String message;


    public ChatResponseDTO(UUID messageId, String sender, Timestamp timestamp, String message) {
        this.messageId = messageId;
        this.sender = sender;
        this.timestamp = timestamp;
        this.message = message;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
