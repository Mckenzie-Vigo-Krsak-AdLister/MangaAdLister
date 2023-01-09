package models;

import java.io.Serializable;

public class Listing extends Manga implements Serializable {

    private long id;
    private long userId;
    private String title;
    private String description;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    private String synopsis;
    private String image;
    private Double price;

    public Listing(String title, String description, String image, Double price, long userId) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.userId = userId;
    }

    public Listing(String title, String description, String image, Double price, long id, long userId) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.id = id;
        this.userId = userId;
    }

    public Listing(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
