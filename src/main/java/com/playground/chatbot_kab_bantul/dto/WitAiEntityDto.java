package com.playground.chatbot_kab_bantul.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WitAiEntityDto {
    private String body;
    private String value;
    private String name;
}
