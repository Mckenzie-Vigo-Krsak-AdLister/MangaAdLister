package Config;

public class Config {

    public String getUrl() {
        return "jdbc:mysql://localhost:3306/manga_adlister?allowPublicKeyRetrieval=true&useSSL=false";
    }
    public String getUser() {
        return "root";
    }

    public String getPassword() {
        return "codeup";
    }
}
