package ru.vlpetko.analyzerspring.constants.bot;

public enum ButtonNameEnum {

    SET_DATA_BUTTON("Добавить новые данные"),

    REDACT_DATA_BUTTON("Редактировать данные"),

    GENERATE_REPORT_BUTTON("Сформировать отчёт"),

    HELP_BUTTON("Помощь");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
