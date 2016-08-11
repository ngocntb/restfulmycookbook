package com.jersey.persistance;

import com.jersey.representations.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Audi on 10/29/2015.
 */
public interface IngredientDao extends JpaRepository<Ingredient, Long> {
}
