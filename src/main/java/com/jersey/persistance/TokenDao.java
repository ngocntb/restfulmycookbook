package com.jersey.persistance;

import com.jersey.representations.Category;
import com.jersey.representations.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Audi on 8/17/2016.
 */
public interface TokenDao extends JpaRepository<Token, Long> {
    @Query("select t from Token t where t.token = :token")
    Token findToken(@Param("token") String token);

}
