package com.andrew.ens.user.adapter.out;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Entity
@Table(name = "users")
public class ENSUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name = "telegram_id", unique = true)
    private long telegramId;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Size(min = 5, max = 32)
    private String username;
}
