package com.playground.chatbot_kab_bantul.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.chatbot_kab_bantul.constant.Intent;
import com.playground.chatbot_kab_bantul.dto.WitAiResponseDto;
import com.playground.chatbot_kab_bantul.service.DataModelService;
import com.playground.chatbot_kab_bantul.service.WitAiService;
import com.playground.chatbot_kab_bantul.util.TextProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class ChatBotController {
    private final WitAiService withAiService;
    private final DataModelService dataModelService;
    private final ObjectMapper objectMapper;


    @GetMapping("/process")
    public String processMessage(@RequestBody String message) {
        log.info("Message: {}", message);
        try {
            String result = "";
            String cleanMessage = TextProcessor.cleanText(message);
            String witAiResponse = withAiService.processMessage(cleanMessage);
            WitAiResponseDto rsp = objectMapper.readValue(witAiResponse, WitAiResponseDto.class);

            String intent = rsp.getIntents().get(0).getName();

            if(intent.isEmpty()){
                return "Maaf harap ulangi prompt anda";
            }

            log.info("rsp: {}", rsp);
            if(Intent.GET_COUNT.getName().equals(intent)){
                result = dataModelService.getCount(rsp);
            }else if(Intent.GET_LIST.getName().equals(intent)){
                result = dataModelService.getList(rsp);

            }

            if(result.isEmpty()){
                return "Tidak ada hasil";
            }

            return result;

        }catch (Exception e) {
            log.error("Error processing message", e);
            return "{\"error\": \"Terjadi kesalahan saat menghubungi Wit.ai\"}";
        }
    }
}
