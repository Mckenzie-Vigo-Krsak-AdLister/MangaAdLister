import apis.myanimelist.ApiHandle;
import apis.myanimelist.ApiHandleImpl;
import dao.ListingsDao;
import dao.ListingsDaoImpl;
import models.Listing;
import models.Manga;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class TestMangaApi {

    private static ApiHandle api;

    private ListingsDao listingsDao;

    @Before
    public void init(){
        this.api = new ApiHandleImpl();
        this.listingsDao = new ListingsDaoImpl();
    }


    @Test
    public void testApiInformationCall() throws IOException, InterruptedException {
        Manga[] manga = api.getMangaContent("pokemon");
        Assert.assertEquals("Mr. Crow, a mysterious man, gathers 3 boys who love Pokémon. Katsuya is the main character, who wants to be stronger. He travels along Toki, who is experienced at traveling, and Soto, a Pokémaniac who...read more.", manga[0].getDescription());
    }

    @Test
    public void testApiInformationCallById() throws IOException, InterruptedException {
        Manga[] manga = api.getMangaContent("yu-gi-oh");
        Manga myManga = api.getMangaContentById(manga[0].getMyanimelist_id());
        Assert.assertEquals("The story takes place in the distant future and focuses on a boy named Yuma Tsukumo, the number one bad boy at his school. Something unexpected happens when Ryouga challenges him to a Duel as the mysterious ghost of Astral appears before them, a new legend begins.", myManga.getSynopsis());
        Assert.assertEquals("https://cdn.myanimelist.net/images/manga/1/178242.jpg", myManga.getPicture_url());
    }

    @Test
    public void testTopFifty() throws IOException, InterruptedException, ExecutionException {
        //Reasonable amount of time getting 50 image urls from the API should take
        int maxReasonableSeconds = 3;

        //Get the top50 mangas from the api
        Manga[] topFifty = api.topFifty();

        //Test how long it takes to get all top 50 manga images calling the API directly
        Date start = Time.from(Instant.now());
        for (Manga manga : topFifty) {
            Manga m = api.getMangaContentById(manga.getMyanimelist_id());
            System.out.println(m.getPicture_url());
        }
        Date end = Time.from(Instant.now());
        Long diff = end.getTime() - start.getTime();
        Long secs = diff / 1000;
        System.out.println("Pulling top 50 from API db took approximately " + secs + " secs to complete.");

        //Make sure we get the right picture url for the 5th item in the top 50
        Manga fifth = topFifty[5];
        String fifthTop50MangaPictureUrl = "https://cdn.myanimelist.net/r/50x70/images/manga/2/258749.jpg?s=fad0d2cae56806beefaca50b445fa0dd";
        Assert.assertEquals(fifthTop50MangaPictureUrl,fifth.getPicture_url());

        //Assert that we get the right picture url after calling getMangaContentById on the 5th item in top50.
        Manga m = api.getMangaContentById(topFifty[5].getMyanimelist_id());
        String fifthTop50MangaContentPictureUrl = "https://cdn.myanimelist.net/images/manga/2/258749.jpg";
        Assert.assertEquals(fifthTop50MangaContentPictureUrl,m.getPicture_url());

        //Check that the API call doesn't take too long to retrieve all 50 images.
        Assert.assertEquals(true, maxReasonableSeconds > secs);

    }

    @Test
    public void testCreateListing() throws SQLException {

        Listing fakeListing = new Listing("Test", "testDescription", "testImg", 9.99, 10, 1);

        boolean created = this.listingsDao.createListing(fakeListing);

        Assert.assertEquals(true, created);

        Listing expectedListing = this.listingsDao.getListingById(10);

//        Assert.assertEquals(fakeListing.getDescription(), expectedListing.getDescription());
        Assert.assertEquals(true, expectedListing != null);

    }

}
