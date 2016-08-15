package com.jersey.representations;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Audi on 11/1/2015.
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name="picture_url")
    private String pictureUrl;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCat;

    @OneToMany(mappedBy = "parentCat")
    private Set<Category> childCats;

/*    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_recipe",
                joinColumns = @JoinColumn(name="category_id"),
                inverseJoinColumns = @JoinColumn(name="recipe_id"))

    private Set<Recipe> recipes;*/

    public Category(){}

    public Category(String name){
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Category> getChildCats() {
        return this.childCats;
    }

    public void setChildCats(Set<Category> childCats) {
        this.childCats = childCats;
    }

    public Category getParentCat() {
        return this.parentCat;
    }

    public void setParentCat(Category parentCat) {
        this.parentCat = parentCat;
    }

/*    public Set<Recipe> getRecipes() {
        return this.recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }*/

}
