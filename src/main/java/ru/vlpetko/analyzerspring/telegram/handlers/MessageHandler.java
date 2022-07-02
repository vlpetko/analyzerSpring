package ru.vlpetko.analyzerspring.telegram.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.vlpetko.analyzerspring.constants.bot.BotMessageEnum;
import ru.vlpetko.analyzerspring.constants.bot.ButtonNameEnum;
import ru.vlpetko.analyzerspring.model.Stock;
import ru.vlpetko.analyzerspring.service.BaseService;
import ru.vlpetko.analyzerspring.service.GenerateFileService;
import ru.vlpetko.analyzerspring.telegram.TelegramApiClient;
import ru.vlpetko.analyzerspring.telegram.buttons.InlineKeyboardMaker;
import ru.vlpetko.analyzerspring.telegram.buttons.ReplyKeyboardMaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.vlpetko.analyzerspring.utils.StockUtils.*;
import static ru.vlpetko.analyzerspring.utils.StockUtils.convertStockToString;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageHandler {

    private final ReplyKeyboardMaker replyKeyboardMaker;
    private final InlineKeyboardMaker inlineKeyboardMaker;
    private final BaseService baseService;
    private final TelegramApiClient telegramApiClient;
    private final GenerateFileService generateFileService;

    public BotApiMethod<?> answerMessage(Message message) throws IOException {
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
            return getRedactMessage(chatId);
        } else if (inputText.startsWith("/newStock")){
            var newStock = convertStringToStock(inputText);
            baseService.saveStock(newStock);
        } else if (inputText.startsWith("/newFile")){
            String[] fileData = inputText.split(",");
            baseService.uploadFile(fileData[1]);
        } else if (inputText.startsWith("/delete")){
            String[] inputData = inputText.split(",");
            baseService.deleteStock(Long.valueOf(inputData[1]));
        } else if (inputText.startsWith("/findStock")){
            String[] inputData = inputText.split(",");
            return getStockByIdMessage(chatId, inputData[1]);
        } else if (inputText.startsWith("/findTicker")){
            String[] inputData = inputText.split(",");
            return getStockByTickerMessage(chatId, inputData[1]);
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

    private SendMessage getRedactMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.REDACT_MENU_MESSAGE.getMessage());
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getInlineRedactButtons("prefix"));
        return sendMessage;
    }
    private SendMessage getStockByIdMessage(String chatId, String stockId) {
        Stock stock = baseService.getStockById(Long.valueOf(stockId));
        SendMessage sendMessage = new SendMessage(chatId, convertStockToString(stock));
        return sendMessage;
    }
    private SendMessage getStockByTickerMessage(String chatId, String stockName) {
        List<Stock> stocks = baseService.getStockByTicker(stockName);
        List<List<String>> stockLists = new ArrayList<>();
        for (Stock st: stocks) {
            List<String> stockList = convertStockToStringList(st);
            stockLists.add(stockList);
        }
        SendMessage sendMessage = new SendMessage();
        if(stocks.size() != 0){
            if(stocks.size() < 2){
                return new SendMessage(chatId, convertStockToString(stocks.get(0)));
            } else {
                try {
                    ByteArrayResource byteArrayResource = generateFileService.generateStocksInfoDocument(stockLists, stockName);
                    telegramApiClient.uploadFile(chatId, byteArrayResource);
                }catch (Exception e){
                    return new SendMessage(chatId, BotMessageEnum.EXCEPTION_UPLOAD_FILE.getMessage());
                }
            }
        }
        return sendMessage;
    }
}
