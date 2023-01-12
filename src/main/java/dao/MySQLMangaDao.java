package dao;


import Config.Config;
import com.mysql.cj.jdbc.Driver;
import models.Manga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class MySQLMangaDao implements Mangas  {
    private Connection connection = null;
    public MySQLMangaDao() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    Config.jdbcConnectionString,
                    Config.mysqlUser,
                    Config.mysqlPassword
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
            ResultSet rs = statement.executeQuery("SELECT * FROM listing");
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

    @Override
    public void deleteManga(String title, int userId) {

    }

//    @Override
//    public void deleteManga(int userId, String title) {
//        try {
//            PreparedStatement stmt = connection.prepareStatement("DELETE FROM listing WHERE user_id = ?");
//            stmt.setLong(1, userId);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Error deleting manga.", e);
//        }
//    }

    private String createInsertQuery(Manga manga) {
        String sql = "INSERT INTO listing(id, title, image, description, price, users_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, manga.getId());
            statement.setString(2, manga.getTitle_ov());
            statement.setString(3, manga.getImage());
            statement.setDouble(4, manga.getPrice());
            statement.setString(5, manga.getDescription());
            statement.setLong(6, manga.getUserId());
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
                rs.getDouble("price"),
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
