package dao;

import Config.Config;

public class DaoFactory {
    private static Mangas mangasDao;

    private static Config config = new Config();

    public static Mangas getMangaDao() {
        if (mangasDao == null) {
            mangasDao = new MySQLMangaDao();
        }
        return mangasDao;
    }
}
