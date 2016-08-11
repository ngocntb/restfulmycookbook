package com.jersey.representations;

import javax.persistence.*;

/**
 * Created by Audi on 11/1/2015.
 */
@Entity
@Table(name = "direction")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name="step")
    private int step;

    @Column(name="picture_url")
    private String pictureUrl;
/*

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
*/

    public Direction(){}

    public Direction(String description){
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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
