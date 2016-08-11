package com.jersey.representations;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by Audi on 11/1/2015.
 */
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "prep")
    private String prep;

    @Column(name = "cooking")
    private String cooking;

    @Column(name = "serving")
    private String serving;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "source_url")
    private String sourceUrl;

    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "upload_date")
    private Date uploadedDate;

/*
    @OneToMany(mappedBy = "recipe")
    private Set<Direction> directions;

    @OneToMany(mappedBy = "recipe")
    private Set<Ingredient> ingredients;
*/

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    @OrderBy("step")
    private Set<Direction> directions;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id")
    @OrderBy("id")
    private Set<Ingredient> ingredients;

    public Recipe(){}

    public Recipe(String name, String prep, String cooking, String serving,
                  String pictureUrl, String videoUrl, String sourceUrl,
                    String sourceName, Date uploadedDate){
        this.name = name;
        this.prep = prep;
        this.cooking = cooking;
        this.serving = serving;
        this.pictureUrl = pictureUrl;
        this.videoUrl = videoUrl;
        this.sourceUrl = sourceUrl;
        this.sourceName = sourceName;
        this.uploadedDate = uploadedDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long mId) {
        this.id = mId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String mName) {
        this.name = mName;
    }

    public String getPrep() {
        return this.prep;
    }

    public void setPrep(String mPrep) {
        this.prep = mPrep;
    }

    public String getCooking() {
        return this.cooking;
    }

    public void setCooking(String cooking) {
        this.cooking = cooking;
    }

    public String getServing() {
        return this.serving;
    }

    public void setServing(String mServing) {
        this.serving = mServing;
    }

    public Set<Direction> getDirections() {
        return this.directions;
    }

    public void setDirections(Set<Direction> directions) {
        this.directions = directions;
    }

    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

}
