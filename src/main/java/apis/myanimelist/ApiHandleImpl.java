package apis.myanimelist;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Manga;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import Config.Config;

public class ApiHandleImpl implements ApiHandle {


    @Override
    public  Manga[] getMangaContent(String title) throws IOException, InterruptedException {
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

    @Override
    public Manga getMangaContentById(Manga myManga) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://myanimelist.p.rapidapi.com/manga/" + myManga.getMyanimelist_id()))
                .header("X-RapidAPI-Key", Config.mangaApiKey)
                .header("X-RapidAPI-Host", Config.mangaApiHost)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Manga manga = mapper.readValue(response.body(), Manga.class);

        return manga;

    }

    @Override
    public Manga[] topFifty() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://myanimelist.p.rapidapi.com/manga/top/manga"))
                        .header("X-RapidAPI-Key", "577da23267mshfa52157d7354156p1f0741jsna34b9317a8ae")
                        .header("X-RapidAPI-Host", "myanimelist.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                //iterate through call prev. function and pass manga
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Manga[] manga = mapper.readValue(response.body(), Manga[].class);
        Manga first = this.getMangaContentById(manga[0]);
        System.out.println(first.getTitle());
//        Manga[] mappedMangas = new Manga[manga.length];
//        int index = 0;
//
//        for (Manga m: manga){
//            mappedMangas[index++] = getMangaContentById(m);
//        }
//        Manga[] mr = (Manga[]) mappedMangas.stream().toArray();
//        return mappedMangas;
        return manga;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ApiHandle api = new ApiHandleImpl();

        Manga[] pokemon_mangas = api.getMangaContent("pokemon");
        for(Manga pokemon_manga : pokemon_mangas){
            System.out.println(pokemon_manga.getMyanimelist_id());
        }

        Manga myManga = api.getMangaContentById(pokemon_mangas[0]);
        System.out.println(myManga.getSynopsis());
        System.out.println(myManga.getPicture_url());
    }



}



