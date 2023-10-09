package com.andrew.ens;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateTransmission {
    void transmit(Update update);
}
