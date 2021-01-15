package com.project.travel.bot.telegram;


import com.project.travel.bot.Repository.TravelRepository;
import com.project.travel.bot.model.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TelegramBotController extends TelegramLongPollingBot {

    @Autowired
    public TravelRepository travelRepository;

    private static final String BOT_NAME = "TravelTaskBot";
    private static final String TOKEN_NAME = System.getenv("TOKEN");
    static {
        ApiContextInitializer.init();
    }

    @PostConstruct
    public void init() {
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return TOKEN_NAME;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<Travel> byCity = travelRepository.findByCity(update.getMessage().getText());
        StringBuilder description = new StringBuilder();

        if (update.getMessage() != null && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            try {
                for (Travel travel : byCity) {
                    description.append("\uD83D\uDDFA Страна:")
                            .append(travel.getCountry())
                            .append("\n")
                            .append("\uD83D\uDCCD Город: ")
                            .append(travel.getCity())
                            .append("\n")
                            .append("\uD83D\uDDEF Описание: ")
                            .append(travel.getCityDescription())
                            .append("\n\n");
                }
                execute(new SendMessage(chatId, " " + description.toString()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    public TelegramBotController() {}
}
