package apis.myanimelist;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Manga;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    public Manga getMangaContentById(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://myanimelist.p.rapidapi.com/manga/" + id))
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
    public Manga[] topFifty() throws IOException, InterruptedException, ExecutionException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://myanimelist.p.rapidapi.com/manga/top/manga"))
                        .header("X-RapidAPI-Key", Config.mangaApiKey)
                        .header("X-RapidAPI-Host", Config.mangaApiHost)
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
                //iterate through call prev. function and pass manga
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Manga[] manga = mapper.readValue(response.body(), Manga[].class);
//        List<Manga> mappedManga = new ArrayList<>();
//        for(Manga m : manga){
//            HttpRequest req = HttpRequest.newBuilder()
//                    .uri(URI.create("https://myanimelist.p.rapidapi.com/manga/" + m.getMyanimelist_id()))
//                    .header("X-RapidAPI-Key", Config.mangaApiKey)
//                    .header("X-RapidAPI-Host", Config.mangaApiHost)
//                    .method("GET", HttpRequest.BodyPublishers.noBody())
//                    .build();
//
//            CompletableFuture resp = HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString());
//            ObjectMapper map = new ObjectMapper();
//            Manga[] mng = map.readValue((JsonParser) resp.get(), Manga[].class);
//            mappedManga.add(mng[0]);
//        }
        return manga;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        ApiHandle api = new ApiHandleImpl();

        Manga[] pokemon_mangas = api.getMangaContent("pokemon");
        for(Manga pokemon_manga : pokemon_mangas){
            System.out.println(pokemon_manga.getMyanimelist_id());
        }

        for(Manga m : pokemon_mangas){
            Manga myManga = api.getMangaContentById(m.getMyanimelist_id());
            System.out.println(myManga.getMyanimelist_id());
        }


        /*
         Proposition:

         Since the API call to retrieve the top 50 mangas takes a little to serve and in the spirit of keeping a smooth
         front end experience, I propose that we can simply do an asynchronous call to this API endpoint using a CRON
         scheduler that calls a servlet endpoint once a day, this will trigger the function that retrieves and stores
         the top 50 mangas into the MySQL database. This will significantly reduce the need to make API calls to once a
         day, since the data will most likely not change within a 24hr period.

         Vigo       [x] Aye   [ ] Ney
         Krsak      [ ] Aye   [ ] Ney
         Mckenzie   [ ] Aye   [ ] Ney

         */
        Manga[] topFifty = api.topFifty();
        for (Manga manga : topFifty) {
              Manga m = api.getMangaContentById(manga.getMyanimelist_id());
              System.out.println(m.getPicture_url());
        }
    }
}



