import apis.myanimelist.ApiHandle;
import apis.myanimelist.ApiHandleImpl;
import models.Manga;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestMangaApi {

    private static ApiHandle api;

    @Before
    public void init(){
        this.api = new ApiHandleImpl();
    }


    @Test
    public void testApiInformationCall() throws IOException, InterruptedException {
        Manga[] manga = api.getMangaContent("pokemon");
        Assert.assertEquals("Mr. Crow, a mysterious man, gathers 3 boys who love Pokémon. Katsuya is the main character, who wants to be stronger. He travels along Toki, who is experienced at traveling, and Soto, a Pokémaniac who...read more.", manga[0].getDescription());
    }
}
