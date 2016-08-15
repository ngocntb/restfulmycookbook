package com.jersey.resources;

import com.jersey.persistance.IngredientDao;
import com.jersey.persistance.RecipeDao;
import com.jersey.representations.Ingredient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Audi on 11/2/2015.
 */
@Path("/ingredients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class IngredientResource {
    private IngredientDao mIngredientDao;
    private RecipeDao mRecipeDao;

    @Inject
    public IngredientResource(IngredientDao ingredientDao, RecipeDao recipeDao){
        mIngredientDao = ingredientDao;
        mRecipeDao = recipeDao;
    }

    /**
     * Get all Ingredients
     * @return Ingredients
     */

    @GET
    public List<Ingredient> getAll(){
        return mIngredientDao.findAll();
    }

/*    @POST
    public Ingredient save(@QueryParam("recipe_id") Long recipeId, @Valid Ingredient ingredient) {
        Recipe recipe = mRecipeDao.findOne(recipeId);
        ingredient.setRecipe(recipe);
        return mIngredientDao.save(ingredient);
    }*/
}
