package Config;

public class Config {

    public String getUrl() {
        return "jdbc:mysql://localhost:3306/manga_adlister?allowPublicKeyRetrieval=true&useSSL=false";
    }
    public String getUser() {
        return "root";
    }

    public String getPassword() {
        return "passwd";
    }

    public static String mangaApiKey = "577da23267mshfa52157d7354156p1f0741jsna34b9317a8ae";
    public static String mangaApiHost = "myanimelist.p.rapidapi.com";
}
