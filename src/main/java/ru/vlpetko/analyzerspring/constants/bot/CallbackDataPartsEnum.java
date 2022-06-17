package ru.vlpetko.analyzerspring.constants.bot;

public enum CallbackDataPartsEnum {

    NEW_MANUAL_STOCK("Ручной ввод"),
    NEW_FILE_STOCK("Загрузка файла");


    private final String message;

    CallbackDataPartsEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
