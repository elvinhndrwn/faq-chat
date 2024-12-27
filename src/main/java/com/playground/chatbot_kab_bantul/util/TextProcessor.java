package com.playground.chatbot_kab_bantul.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
@SuppressWarnings("all")
public class TextProcessor {
    // Daftar kata imbuhan atau stopword yang ingin dihapus
    private static final String[] STOPWORDS = {"di", "yang", "dan", "dengan", "sih"};

    // Metode untuk menghapus stopwords dari teks
    public static String cleanText(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Gabungkan stopwords menjadi satu regex dengan boundary untuk awal kata atau kata tunggal
        String stopwordsPattern = "\\b(" + String.join("|", STOPWORDS) + ")(?=\\s|\\b)";

        // Hapus stopwords menggunakan regex (case-insensitive)
        input = input.replaceAll("(?i)" + stopwordsPattern, "").trim();

        // Tangani kasus stopwords di awal kata (misalnya, "dibanguntapan" menjadi "banguntapan")
        for (String stopword : STOPWORDS) {
            input = input.replaceAll("(?i)\\b" + Pattern.quote(stopword) + "(\\w+)", "$1").trim();
        }

        // Hapus spasi ganda yang mungkin muncul setelah penghapusan
        input = input.replaceAll("\\s{2,}", " ").trim();

        // Logging hasil bersih
        log.info("Clean Message: {}", input);
        return input.toLowerCase();
    }
}
