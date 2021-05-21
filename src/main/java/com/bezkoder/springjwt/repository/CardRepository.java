package com.bezkoder.springjwt.repository;


import java.util.List;

import com.bezkoder.springjwt.models.Card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(value="SELECT * FROM cards u WHERE u.card_user = ?1", nativeQuery = true)
    List<Card> findByCardUser(long card_user);

}
