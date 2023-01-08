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

    private String synopsis;

    private long id;
    private long userId;
    private String title;
    private String description;
    private String image;
    private String price;

    public int getMyanimelist_id() {
        return myanimelist_id;
    }

    public void setMyanimelist_id(int myanimelist_id) {
        this.myanimelist_id = myanimelist_id;
    }

    private int myanimelist_id;

    public Manga(String title, String description, String image, String price, long id, long userId) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.id = id;
        this.userId = userId;
    }

    public Manga(){}

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getUserId() {
        return String.valueOf(userId);
    }

}
