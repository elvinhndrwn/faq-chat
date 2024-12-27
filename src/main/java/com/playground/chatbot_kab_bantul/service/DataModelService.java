package com.playground.chatbot_kab_bantul.service;

import com.playground.chatbot_kab_bantul.dto.WitAiResponseDto;

public interface DataModelService {
    String getName(String region, String location, String period);
    String getCount(WitAiResponseDto rsp);

    String getList(WitAiResponseDto rsp);
}
