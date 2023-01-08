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

    @Test
    public void testApiInformationCallById() throws IOException, InterruptedException {
        Manga[] manga = api.getMangaContent("yu-gi-oh");
        Manga myManga = api.getMangaContentById(manga[0]);
        Assert.assertEquals("The story takes place in the distant future and focuses on a boy named Yuma Tsukumo, the number one bad boy at his school. Something unexpected happens when Ryouga challenges him to a Duel as the mysterious ghost of Astral appears before them, a new legend begins.", myManga.getSynopsis());
        Assert.assertEquals("https://cdn.myanimelist.net/images/manga/1/178242.jpg", myManga.getPicture_url());
    }

    @Test
    public void testTopFifty() throws IOException, InterruptedException{
        Manga manga = api.topFifty()[6];
        Assert.assertEquals("Slam Dunk", manga.getTitle());
    }
}
