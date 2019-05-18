package com.chatbot.chatBot.controller;

import com.chatbot.chatBot.DTO.ChatRequestDTO;
import com.chatbot.chatBot.DTO.ChatResponseDTO;
import com.chatbot.chatBot.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatBotController {

    @Autowired
    ChatBotService chatBotService;

    @PostMapping("/sendmsg")
    public ChatResponseDTO sendMsg(@RequestBody ChatRequestDTO chatRequestDTO){

        return   chatBotService.sendMessage(chatRequestDTO);

    }


}
