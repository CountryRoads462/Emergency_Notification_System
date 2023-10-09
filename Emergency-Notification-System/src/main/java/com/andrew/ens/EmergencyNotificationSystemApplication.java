package com.andrew.ens;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@RequiredArgsConstructor
public class EmergencyNotificationSystemApplication {

	@Autowired
	private final UpdateTransmission updateTransmission;

	public static void main(String[] args) {
		SpringApplication.run(EmergencyNotificationSystemApplication.class, args);
	}

	@Bean
	TelegramLongPollingBot telegramBot(
			@Value("${app.telegram.token}") String botToken,
			@Value("${app.telegram.username}") String botUsername
	) throws TelegramApiException {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		TelegramLongPollingBot bot = new TelegramLongPollingBot(botToken) {

			@Override
			public void onUpdateReceived(Update update) {
				updateTransmission.transmit(update);
			}

			@Override
			public String getBotUsername() {
				return botUsername;
			}
		};

		telegramBotsApi.registerBot(bot);
		return bot;
	}
}
