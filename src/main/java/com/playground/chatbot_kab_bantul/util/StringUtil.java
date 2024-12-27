package com.playground.chatbot_kab_bantul.util;

import com.playground.chatbot_kab_bantul.dto.WitAiEntityDto;

public class StringUtil {
    public StringUtil() {
    }

    public static String defaultString(WitAiEntityDto text) {
        return text.getValue() == null ? "" : text.getValue();
    }
}
