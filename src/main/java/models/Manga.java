package models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Manga implements Serializable {

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    private String picture_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    private String synopsis;

    private long id;
    private long userId;
    private String title_ov;
    private String description;
    private String image;
    private Double price;

    private int myanimelist_id;

    public Manga(String title, String description, String image, Double price, long id, long userId) {
        this.title_ov = title;
        this.synopsis = description;
        this.image = image;
        this.price = price;
        this.id = id;
        this.userId = userId;
    }

    public Manga(){}

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle_ov() {
        return title_ov ;
    }

    public void setTitle_ov(String title){
        this.title_ov = title;
    }

    public int getMyanimelist_id() {
        return myanimelist_id;
    }

    public void setMyanimelist_id(int myanimelist_id) {
        this.myanimelist_id = myanimelist_id;
    }


}
