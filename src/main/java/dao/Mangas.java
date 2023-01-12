package dao;

import models.Manga;

import java.util.List;

public interface Mangas {

    List<Manga> all();

    Long insert(Manga manga);

    void deleteManga(String title, int userId);

}
