package ru.vlpetko.analyzerspring.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.vlpetko.analyzerspring.telegram.StockBot;

@RestController
@AllArgsConstructor
public class WebHookController {

    private final StockBot stockBot;

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return stockBot.onWebhookUpdateReceived(update);
    }
}

