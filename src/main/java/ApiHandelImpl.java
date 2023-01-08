import com.fasterxml.jackson.databind.ObjectMapper;
import managers.Manga;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiHandelImpl implements ApiHandel {


    @Override
    public Manga getMangaContent() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://myanimelist.p.rapidapi.com/manga/search/pokemon"))
                .header("X-RapidAPI-Key", "577da23267mshfa52157d7354156p1f0741jsna34b9317a8ae")
                .header("X-RapidAPI-Host", "myanimelist.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        Manga manga = mapper.readValue(response.body(), Manga.class);
        System.out.println(manga);

        return manga;

        }

    }

