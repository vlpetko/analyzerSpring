package ru.vlpetko.analyzerspring.telegram.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.vlpetko.analyzerspring.constants.bot.CallbackDataPartsEnum;
import ru.vlpetko.analyzerspring.telegram.TelegramApiClient;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {

    private TelegramApiClient telegramApiClient;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();
        System.out.println(data);
        if(data.equals("prefix" + CallbackDataPartsEnum.NEW_MANUAL_STOCK.getCallbackDataParts())){
            return setManualFile(chatId);
        } else if(data.equals("prefix" + CallbackDataPartsEnum.NEW_FILE_STOCK.getCallbackDataParts())){
            return setUploadFile(chatId);
        } else {
            return null;
        }
    }

    private SendMessage setManualFile(String chatId){
        return new SendMessage(chatId, "Введите в строке команду /newStock и данные акции через запятую");
    }
    private SendMessage setUploadFile(String chatId){
        return new SendMessage(chatId, "Введите в строке команду /newFile и путь к файлу после запятой");
    }
}
