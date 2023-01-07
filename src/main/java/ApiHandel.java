import managers.Manga;

import java.io.IOException;

public interface ApiHandel {

   Manga getMangaContent() throws IOException, InterruptedException;

}
