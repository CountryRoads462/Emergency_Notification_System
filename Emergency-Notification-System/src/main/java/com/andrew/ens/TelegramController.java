package com.andrew.ens;

import com.andrew.ens.user.application.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TelegramController implements
        UpdateTransmission {

    @Autowired
    private final UsersService usersService;

    @Override
    public void transmit(Update update) {
        Menu currentMenu = Menu.USER;

        switch (currentMenu) {
            case USER -> usersController(update);
        }
    }

    private void usersController(Update update) {
        usersService.createUser(update);
    }
}
