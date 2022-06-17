package ru.vlpetko.analyzerspring.telegram.buttons;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.vlpetko.analyzerspring.constants.bot.BotMessageEnum;
import ru.vlpetko.analyzerspring.constants.bot.CallbackDataPartsEnum;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboardMaker {

    public InlineKeyboardMarkup getInlineMessageButtonsWithTemplate(String prefix, boolean isUser){
        InlineKeyboardMarkup inlineKeyboardMarkup = getInlineMessageButtons(prefix, isUser);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineMessageButtons(String prefix, boolean isUserDictionaryNeed) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

       rowList.add(getButton(CallbackDataPartsEnum.NEW_MANUAL_STOCK.getMessage(),
               prefix + CallbackDataPartsEnum.NEW_MANUAL_STOCK.getMessage()));
       rowList.add(getButton(CallbackDataPartsEnum.NEW_FILE_STOCK.getMessage(),
               prefix + CallbackDataPartsEnum.NEW_FILE_STOCK.getMessage()));
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
