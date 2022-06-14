package ru.vlpetko.analyzerspring.telegram.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.vlpetko.analyzerspring.telegram.TelegramApiClient;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {

    private TelegramApiClient telegramApiClient;
}
