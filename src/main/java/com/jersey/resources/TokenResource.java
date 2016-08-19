package com.jersey.resources;

import com.jersey.persistance.TokenDao;
import com.jersey.representations.Token;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Audi on 8/17/2016.
 */
@Path("/gcm")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class TokenResource {
    private TokenDao mTokenDao;

    @Inject
    public TokenResource(TokenDao tokenDao) {
        mTokenDao = tokenDao;
    }

    /**
     * Get tokens
     * @return tokens
     */
    @GET
    public List<Token> getAll(){
        return mTokenDao.findAll();
    }

    /**
     * Save GCM Token
     * @return GCM Token
     */
    @POST
    public Token save(@Valid Token token) {
        return mTokenDao.save(token);
    }

    /**
     * Delete gcmToken
     * @param token
     */
    @DELETE
    public void delete(@Valid Token token) {
        Token gcmToken = mTokenDao.findOne(token.getId());
        if(gcmToken == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else {
            mTokenDao.delete(gcmToken);
        }
    }
}
