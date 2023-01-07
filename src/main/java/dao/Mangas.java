package dao;

import managers.Manga;

import java.util.List;

public interface Mangas {

    List<Manga> all();

    Long insert(Manga manga);

}
