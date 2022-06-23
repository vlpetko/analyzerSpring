package ru.vlpetko.analyzerspring.telegram.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.vlpetko.analyzerspring.constants.bot.BotMessageEnum;
import ru.vlpetko.analyzerspring.constants.bot.ButtonNameEnum;
import ru.vlpetko.analyzerspring.constants.bot.CallbackDataPartsEnum;
import ru.vlpetko.analyzerspring.service.BaseService;
import ru.vlpetko.analyzerspring.telegram.buttons.InlineKeyboardMaker;
import ru.vlpetko.analyzerspring.telegram.buttons.ReplyKeyboardMaker;
import ru.vlpetko.analyzerspring.utils.StockUtils;

import static ru.vlpetko.analyzerspring.utils.StockUtils.convertSringToStock;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageHandler {

    private final ReplyKeyboardMaker replyKeyboardMaker;
    private final InlineKeyboardMaker inlineKeyboardMaker;
    private  final BaseService baseService;


    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();
        log.info("ChatId is: {}", chatId);

        String inputText = message.getText();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return getStartMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.SET_DATA_BUTTON.getButtonName())) {
            return getDataMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.REDACT_DATA_BUTTON.getButtonName())) {
            return getDataMessage(chatId);
        } else if (inputText.startsWith("/newStock")){
            var newStock = convertSringToStock(inputText);
            baseService.saveStock(newStock);
        } else if (inputText.startsWith("/newFile")){
            String[] fileData = inputText.split(",");
            baseService.uploadFile(fileData[1]);
        } else {
            System.out.println("Fucking text");
        }
        return null;
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getDataMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.CHOOSE_METHOD_MESSAGE.getMessage());
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getInlineMessageButtons("prefix", true
        ));
        return sendMessage;
    }
}
