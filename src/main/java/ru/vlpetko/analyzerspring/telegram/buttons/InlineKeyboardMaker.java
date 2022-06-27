package ru.vlpetko.analyzerspring.telegram.buttons;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.vlpetko.analyzerspring.constants.bot.BotMessageEnum;
import ru.vlpetko.analyzerspring.constants.bot.CallbackDataPartsEnum;

import java.util.ArrayList;
import java.util.List;

import static ru.vlpetko.analyzerspring.constants.bot.CallbackDataPartsEnum.*;

@Component
public class InlineKeyboardMaker {

    public InlineKeyboardMarkup getInlineMessageButtons(String prefix, boolean isUserDictionaryNeed) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

       rowList.add(getButton(NEW_MANUAL_STOCK.getCallbackDataParts(),
               prefix + NEW_MANUAL_STOCK.getCallbackDataParts()));
       rowList.add(getButton(NEW_FILE_STOCK.getCallbackDataParts(),
               prefix + NEW_FILE_STOCK.getCallbackDataParts()));
       InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(rowList);
       return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineRedactButtons(String prefix){
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        rowList.add(getButton(REDACT_STOCK.getCallbackDataParts(),
                prefix + REDACT_STOCK.getCallbackDataParts()));
        rowList.add(getButton(GET_ALL_STOCKS.getCallbackDataParts(),
                prefix + GET_ALL_STOCKS.getCallbackDataParts()));
        rowList.add(getButton(FIND_STOCK_BY_ID.getCallbackDataParts(),
                prefix + FIND_STOCK_BY_ID.getCallbackDataParts()));
        rowList.add(getButton(DELETE_STOCK.getCallbackDataParts(),
                prefix + DELETE_STOCK.getCallbackDataParts()));
        rowList.add(getButton(FIND_STOCK_BY_TICKER.getCallbackDataParts(),
                prefix + FIND_STOCK_BY_TICKER.getCallbackDataParts()));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(rowList);
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getButton(String buttonName, String buttonCallBackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonName);
        button.setCallbackData(buttonCallBackData);

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }
}
