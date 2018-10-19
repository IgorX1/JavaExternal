package com.javacourse;

public enum LanguageEnum {
    EN("en"),
    RU("ru"),
    UA("ukr");

    private String langCode;

    LanguageEnum(String langCode) {
        this.langCode = langCode;
    }

    public String getLangCode() {
        return langCode;
    }
}
