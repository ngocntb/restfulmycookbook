package com.jersey.resources;

import com.jersey.persistance.*;
import com.jersey.representations.Recipe;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 ** Created by Audi on 11/2/2015.
 */
@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class RecipeResource {
    private RecipeDao mRecipeDao;

    @Inject
    public RecipeResource(RecipeDao recipeDao) {
        mRecipeDao = recipeDao;
    }

    /**
     * Get all Recipes
     * @return Recipes
     */

    @GET
    public List<Recipe> getAll(){
        return mRecipeDao.findAll();
    }

    /**
     * Get All Recipes
     * @param page - page number
     *        limit - max number per page
     * @return Recipes
     */

    @GET
    @Path("/recents")
    public List<Recipe> getRecents(@QueryParam("page") Integer page, @QueryParam("limit") Integer limit){
        Pageable pageable = new PageRequest(page, limit);
        return mRecipeDao.getRecentRecipes(pageable);
    }

    /**
     * Get Recipes of Category
     * @param categoryId - category id
     *        page - page number
     *        limit - max number per page
     * @return category that include its recipes
     */

    @GET
    @Path("/category")
    public List<Recipe>  getRecipesByCategory(@QueryParam("id")Long categoryId,
                                       @QueryParam("page") Integer page,
                                       @QueryParam("limit") Integer limit) {
        Pageable pageable = new PageRequest(page, limit);
        List<Recipe> recipes = mRecipeDao.getRecipesCategory(categoryId, pageable);
        if (recipes == null) {
            throw new WebApplicationException((Response.Status.NOT_FOUND));
        }
        return recipes;
    }

    /**
     * Get Ingredients of Recipe
     * @param id - recipe id
     * @return Recipe
     */

    @GET
    @Path("/ingredients")
    public Recipe getAllIngredients(@QueryParam("id") Long id) {
        Recipe recipe = mRecipeDao.getIngredientsByRecipe(id);
        if (recipe == null) {
            throw new WebApplicationException((Response.Status.NOT_FOUND));
        }

        return recipe;
    }

    /**
     * Get Directions of Recipe
     * @param id - recipe id
     * @return Recipe
     */

    @GET
    @Path("/directions")
    public Recipe getAllDirectionsForRecipe(@QueryParam("id") Long id) {
        Recipe recipe = mRecipeDao.getDirectionsByRecipe(id);
        if (recipe == null) {
            throw new WebApplicationException((Response.Status.NOT_FOUND));
        }

        return recipe;
    }

    /**
     * Get Recipe
     * @param id - recipe id
     * @return Recipe
     */

    @GET
    @Path("{id}")
    public Recipe getRecipe(@PathParam("id")long id) {
        Recipe recipe = mRecipeDao.findOne(id);
        if (recipe == null) {
            throw new WebApplicationException((Response.Status.NOT_FOUND));
        }
        return recipe;
    }

    /**
     * Save Recipe
     * @param recipe - recipe id
     * @return Recipe
     */

    @POST
    public Recipe save(@Valid Recipe recipe) {
        return mRecipeDao.save(recipe);
    }
}
