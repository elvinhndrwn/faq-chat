package com.playground.chatbot_kab_bantul.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
@SuppressWarnings("all")
public enum Intent {
    GET_COUNT("get_count"),
    GET_NAME("get_name"),
    GET_LIST("get_list"),
    ;

    private final String name;

    Intent(String name) {
        this.name = name;
    }
}
