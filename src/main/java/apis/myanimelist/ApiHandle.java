package apis.myanimelist;

import models.Manga;

import java.io.IOException;

public interface ApiHandle {

   Manga[] getMangaContent(String title) throws IOException, InterruptedException;

    Manga getMangaContentById(Manga myManga) throws IOException, InterruptedException;

   Manga[] topFifty()throws IOException, InterruptedException;

}
