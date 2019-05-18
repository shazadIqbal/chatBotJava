package com.chatbot.chatBot.service;

import com.chatbot.chatBot.DTO.ChatRequestDTO;
import com.chatbot.chatBot.DTO.ChatResponseDTO;
import com.chatbot.chatBot.DialogFlow.DialogFlowRequestDTO;
import com.chatbot.chatBot.DialogFlow.DialogFlowResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class ChatBotService {

       @Value("${client-access-token}")
        private String clienttoken;

        private RestTemplate restTemplate = new RestTemplate();
        private HttpHeaders headers = new HttpHeaders();
        private String url = "https://api.dialogflow.com/v1/query?v=20150910";


        public ChatResponseDTO sendMessage(ChatRequestDTO chatRequestDTO){
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setBearerAuth(clienttoken);

            DialogFlowRequestDTO request = new DialogFlowRequestDTO();
            request.setLang("en");
            request.setQuery(chatRequestDTO.getMessage());
            request.setSessionId("1234");
            request.setTimezone(chatRequestDTO.getTimestamp().toString());

            HttpEntity<DialogFlowRequestDTO> entity = new HttpEntity<>(request, headers);

            //restTemplate.
            ResponseEntity<DialogFlowResponseDTO> response = restTemplate.postForEntity(url,entity,DialogFlowResponseDTO.class );
            UUID messageId =  UUID.fromString(response.getBody().getId());//Long.parseLong();//parseInt(response.getBody().getId());
            String sender = response.getBody().getResult().getSource();
            String message = response.getBody().getResult().getFulfillment().getSpeech();

            SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date stringDate = null;
            try {
                stringDate = datetimeFormatter1.parse(response.getBody().getTimestamp());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp timestamp = new Timestamp(stringDate.getTime());



            return new ChatResponseDTO(messageId,sender,timestamp,message);

        }
}
