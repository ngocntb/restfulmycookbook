package com.jersey.representations;

import javax.persistence.*;

/**
 * Created by Audi on 11/1/2015.
 */
@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", columnDefinition = "text")
    private String description;

/*    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;*/

    public Ingredient(){}

    public Ingredient(String description){
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
