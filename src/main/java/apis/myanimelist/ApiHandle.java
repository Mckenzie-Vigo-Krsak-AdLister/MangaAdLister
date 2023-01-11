package apis.myanimelist;

import models.Manga;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ApiHandle {

//   static Manga[] getMangaContent(String title) throws IOException, InterruptedException;

    Manga[] getMangaContent(String title) throws IOException, InterruptedException;

    Manga getMangaContentById(int id) throws IOException, InterruptedException;

   Manga[] topFifty() throws IOException, InterruptedException, ExecutionException;

}
