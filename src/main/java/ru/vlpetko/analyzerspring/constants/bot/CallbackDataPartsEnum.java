package ru.vlpetko.analyzerspring.constants.bot;

public enum CallbackDataPartsEnum {

    NEW_MANUAL_STOCK("Ручной ввод"),
    NEW_FILE_STOCK("Загрузка файла");

    private final String CallbackDataParts;

    CallbackDataPartsEnum(String callbackDataParts)  {
        this.CallbackDataParts = callbackDataParts;
    }

    public String getCallbackDataParts() {
        return CallbackDataParts;
    }
}

