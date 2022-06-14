package ru.vlpetko.analyzerspring.constants.bot;

public enum ButtonNameEnum {
    GET_TASKS_BUTTON("Добавить новые данные"),
    GET_DICTIONARY_BUTTON("Редактировать данные"),
    UPLOAD_DICTIONARY_BUTTON("Сформировать отчёт"),
    HELP_BUTTON("Помощь");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}