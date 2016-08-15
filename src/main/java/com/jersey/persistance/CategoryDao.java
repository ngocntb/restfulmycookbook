package com.jersey.persistance;

import com.jersey.representations.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by Audi on 11/1/2015.
 */
public interface CategoryDao extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.parentCat is null")
    List<Category> findParentCats();

    @Query("SELECT c FROM Category c where c.parentCat = :parentCat")
    List<Category> getSubCats(@Param("parentCat") Category parentCat);

/*    @Query("SELECT c FROM Category c JOIN FETCH c.recipes WHERE c.id = :id")
    Category getRecipesCategory(@Param("id") Long id, Pageable pageable);*/
}
