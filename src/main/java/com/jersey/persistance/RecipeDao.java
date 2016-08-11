package com.jersey.persistance;

import com.jersey.representations.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Audi on 10/29/2015.
 */
public interface RecipeDao extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r order by r.uploadedDate desc, r.id asc")
    List<Recipe> getRecentRecipes(Pageable pageable);

    @Query("SELECT r FROM Recipe r JOIN FETCH r.ingredients i WHERE r.id = :id order by i.id asc")
    Recipe getIngredientsByRecipe(@Param("id") Long id);

    @Query("SELECT r FROM Recipe r JOIN FETCH r.directions d WHERE r.id = :id order by d.step asc")
    Recipe getDirectionsByRecipe(@Param("id") Long id);
}
