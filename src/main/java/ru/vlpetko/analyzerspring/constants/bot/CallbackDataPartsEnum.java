package ru.vlpetko.analyzerspring.constants.bot;

public enum CallbackDataPartsEnum {

    NEW_MANUAL_STOCK("Ручной ввод"),
    NEW_FILE_STOCK("Загрузка файла"),
    REDACT_STOCK("Редактировать запись"),
    GET_ALL_STOCKS("Показать все записи"),
    FIND_STOCK_BY_ID("Найти запись по идентификатору"),
    DELETE_STOCK("Удалить запись"),
    FIND_STOCK_BY_TICKER("Найти запись по наименованию");

    private final String CallbackDataParts;

    CallbackDataPartsEnum(String callbackDataParts)  {
        this.CallbackDataParts = callbackDataParts;
    }

    public String getCallbackDataParts() {
        return CallbackDataParts;
    }
}

