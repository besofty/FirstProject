package com.besofty.firstproject.book.enums;

public enum BookErrorEnum {
    NOT_EXIST("书籍不存在！"),
    ISBN_EXISTED("书编已存在！"),;

    private String message;

    BookErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
