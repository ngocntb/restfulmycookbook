package com.jersey.resources;

import com.jersey.persistance.CategoryDao;
import com.jersey.representations.Category;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Audi on 11/2/2015.
 */
@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class CategoryResource {
    private CategoryDao mCategoryDao;

    @Inject
    public CategoryResource(CategoryDao categoryDao) {
        mCategoryDao = categoryDao;
    }

    /**
     * Get all Categories
     * @return categories
     */
    @GET
    public List<Category> getAll(){
        return mCategoryDao.findAll();
    }

    /**
     * Get Level 1 Categories
     * @return categories
     */

    @GET
    @Path("/parent")
    public List<Category> getParent(){
        return mCategoryDao.findParentCats();
    }

    /**
     * Get Level 2 Categories
     * @param parent_id
     * @return categories
     */

    @GET
    @Path("/subcategories")
    public List<Category> getSubCats(@QueryParam("parent_id")Long parent_id){
        Category parentCat = mCategoryDao.getOne(parent_id);
        return mCategoryDao.getSubCats(parentCat);
    }
    /**
     * Get single Category
     * @param id
     * @return category
     */

    @GET
    @Path("{id}")
    public Category getOne(@PathParam("id")Long id) {
        Category category = mCategoryDao.findOne(id);
        if (category == null) {
            throw new WebApplicationException((Response.Status.NOT_FOUND));
        }
        return category;
    }

    /**
     * Get Recipes of Category
     * @param id - category id
     *        page - page number
     *        limit - max number per page
     * @return category that include its recipes
     */

/*    @GET
    @Path("/recipes")
    public Category getRecipesCategory(@QueryParam("id")Long id,
                                       @QueryParam("page") Integer page,
                                       @QueryParam("limit") Integer limit) {
        Pageable pageable = new PageRequest(page, limit);
        Category category = mCategoryDao.getRecipesCategory(id, pageable);
        if (category == null) {
            throw new WebApplicationException((Response.Status.NOT_FOUND));
        }
        return category;
    }*/

    /**
     * Save category
     * @param category
     * @return new category
     */
    @POST
    public Category save(@Valid Category category) {
        return mCategoryDao.save(category);
    }

    /**
     * Update existing category
     * @param id
     * @param category
     * @return updated category
     */
    @PUT
    @Path("{id}")
    public Category update(@PathParam("id")long id, @Valid Category category) {
        if(mCategoryDao.findOne(id) == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            category.setId(id);
            return mCategoryDao.save(category);
        }
    }

    /**
     * Delete category
     * @param id
     */
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id")long id) {
        Category category = mCategoryDao.findOne(id);
        if(category== null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            mCategoryDao.delete(category);
        }
    }

    @POST
    @Path("/add")
    public Category addNewChildCategory(@QueryParam("parent_id")Long parent_id, @Valid Category category) {
        Category parentCat = getOne(parent_id);
        category.setParentCat(parentCat);
        return mCategoryDao.save(category);
    }
}
