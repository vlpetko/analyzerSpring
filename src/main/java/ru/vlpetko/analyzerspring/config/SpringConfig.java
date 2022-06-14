package ru.vlpetko.analyzerspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.vlpetko.analyzerspring.telegram.StockBot;
import ru.vlpetko.analyzerspring.telegram.handlers.CallbackQueryHandler;
import ru.vlpetko.analyzerspring.telegram.handlers.MessageHandler;

@Configuration
public class SpringConfig {

    @Value("${webhook-path}")
    String webhookPath;
    @Value("${bot-name}")
    String botName;
    @Value("${bot-token}")
    String botToken;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(webhookPath).build();
    }

    @Bean
    public StockBot springWebookBot(SetWebhook setWebhook,
                                    MessageHandler messageHandler,
                                    CallbackQueryHandler callbackQueryHandler){
        StockBot bot = new StockBot(setWebhook, messageHandler, callbackQueryHandler);

        bot.setBotPath(webhookPath);
        bot.setBotUsername(botName);
        bot.setBotToken(botToken);

        return bot;
    }
}
