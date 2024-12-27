package com.playground.chatbot_kab_bantul.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WitAiResponseDto {

    private Map<String, List<WitAiEntityDto>> entities;
    private List<WitAiIntentDto> intents;
    private String text;
}
