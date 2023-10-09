package com.andrew.ens.user.application;

import com.andrew.ens.user.adapter.out.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@RequiredArgsConstructor
public class UsersService {

    @Autowired
    private final UsersRepository usersRepo;

    public void createUser(Update update) {
        User user = update.getMessage().getFrom();

        usersRepo.createUser(
                user.getFirstName(),
                user.getLastName(),
                user.getId(),
                user.getUserName()
        );
    }
}
