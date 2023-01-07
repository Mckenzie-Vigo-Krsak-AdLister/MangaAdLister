package managers;
import java.io.Serializable;

public class Manga implements Serializable {

    private long id;
    private long userId;
    private String title;
    private String description;
    private String image;
    private String price;

    public Manga(String title, String description, String image, String price, long id, long userId) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.price = price;
        this.id = id;
        this.userId = userId;
    }

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
