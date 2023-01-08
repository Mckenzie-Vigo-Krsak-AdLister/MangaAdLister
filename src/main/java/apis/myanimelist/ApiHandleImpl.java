package apis.myanimelist;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Manga;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import Config.Config;

public class ApiHandleImpl implements ApiHandle {


    @Override
    public Manga[] getMangaContent(String title) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://myanimelist.p.rapidapi.com/manga/search/" + title))
                .header("X-RapidAPI-Key", Config.mangaApiKey)
                .header("X-RapidAPI-Host", Config.mangaApiHost)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Manga[] manga = mapper.readValue(response.body(), Manga[].class);
        return manga;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ApiHandle api = new ApiHandleImpl();
        Manga[] pokemon_mangas = api.getMangaContent("pokemon");
        for(Manga pokemon_manga : pokemon_mangas){
            System.out.println(pokemon_manga.getDescription());
        }
    }
}



