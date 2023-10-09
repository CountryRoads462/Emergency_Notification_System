package com.andrew.ens.user.adapter.out;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends CrudRepository<ENSUser, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users(first_name, last_name, telegram_id, username)" +
            "VALUES(:firstName, :lastName, :telegramId, :username)" +
            "ON CONFLICT DO NOTHING;", nativeQuery = true)
    void createUser(String firstName, String lastName, Long telegramId, String username);
}
