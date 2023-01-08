package dao;


import Config.Config;
import com.mysql.cj.jdbc.Driver;
import managers.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLMangaDao implements Mangas  {
    private Connection connection = null;

    public MySQLMangaDao(Config config) {

        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );

        } catch (SQLException e) {
            throw new RuntimeException("DataBase Error", e);
        }

    }

    @Override
    public List<Manga> all() {
        Statement statement =  null;

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM manga_adlister.listing");
            return createMangaFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving mangas.", e);
        }
    }

    @Override
    public Long insert(Manga manga) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createInsertQuery(manga), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new manga.", e);
        }
    }

    private String createInsertQuery(Manga manga) {
        String sql = "INSERT INTO manga_adlister.listing(id, title, image, description, price, users_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, manga.getId());
            statement.setString(2, manga.getTitle());
            statement.setString(3, manga.getImage());
            statement.setString(4, manga.getPrice());
            statement.setString(5, manga.getDescription());
            statement.setString(6, manga.getUserId());
            return statement.toString();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new manga.", e);
        }
    }

    private Manga extractManga(ResultSet rs) throws SQLException {
        return new Manga(
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("image"),
                rs.getString("price"),
                rs.getLong("id"),
                rs.getLong("users_id")
        );
    }

    private List<Manga> createMangaFromResults(ResultSet rs) throws SQLException {
        List<Manga> mangas = new ArrayList<>();
        while (rs.next()) {
            mangas.add(extractManga(rs));
        }
        return mangas;
    }

}
