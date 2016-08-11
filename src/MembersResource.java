package com.jersey.resources;


import com.jersey.persistance.MemberDao;
import com.jersey.persistance.ProductDao;
import com.jersey.representations.Member;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@Transactional
public class MembersResource {
    private final MemberDao memberDao;
    private final ProductDao productDao;

    @Inject
    public MembersResource(MemberDao memberDao, ProductDao productDao) {
        this.memberDao = memberDao;
        this.productDao = productDao;
    }
    @GET
    public List<Member> getAll(){
        return this.memberDao.findAll();
    }

    @GET
    @Path("{id}/products")
    public Member getAllProductsForMember(@PathParam("id")long id) {
        Member member = memberDao.findOne(id);
        if (member == null) {
            throw new WebApplicationException((Response.Status.NOT_FOUND));
        }

        //Poke products
        member.getProducts().size();
        return member;
    }

    @GET
    @Path("{id}")
    public Member getMember(@PathParam("id")long id) {
        Member member = memberDao.findOne(id);
        if (member == null) {
            throw new WebApplicationException((Response.Status.NOT_FOUND));
        }
        return member;
    }
}