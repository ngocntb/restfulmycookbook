package com.jersey.resources;

import com.jersey.persistance.DirectionDao;
import com.jersey.persistance.RecipeDao;
import com.jersey.representations.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Audi on 11/2/2015.
 */
@Path("/directions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class DirectionResource {
    private DirectionDao mDirectionDao;
    private RecipeDao mRecipeDao;

    @Inject
    public DirectionResource(DirectionDao directionDao, RecipeDao recipeDao){
        mDirectionDao = directionDao;
        mRecipeDao = recipeDao;
    }

    /**
     * Get all Directions
     * @return Directions
     */

    @GET
    public List<Direction> getAll(){
        return mDirectionDao.findAll();
    }

/*    @POST
    public Direction save(@QueryParam("recipe_id") Long recipeId, @Valid Direction direction) {
        Recipe recipe = mRecipeDao.findOne(recipeId);
        direction.setRecipe(recipe);
        return mDirectionDao.save(direction);
    }*/

}
