package apis.myanimelist;

import models.Manga;

import java.io.IOException;

public interface ApiHandle {

   Manga[] getMangaContent(String title) throws IOException, InterruptedException;

}
