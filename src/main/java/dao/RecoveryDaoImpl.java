package dao;

import Config.Config;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class RecoveryDaoImpl implements RecoveryDao {
    Connection connection = null;

    public RecoveryDaoImpl() throws SQLException {
        try{
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(Config.jdbcConnectionString, Config.mysqlUser, Config.mysqlPassword);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public String getRecoverCodeForUserId(int userid) throws SQLException {
        try {
            //Grab the recovery for the user with the passed user id
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM recovery WHERE userid=?;");
            stmt.setInt(1, userid);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();

            //Grab the code from that recovery entry
            return rs.getString("code");
        }catch(SQLException e){
            throw e;
        }
    }

    @Override
    public int getUserIdForCode(String code) throws SQLException {
        try {
            //Grab the recovery for the user with the passed user id
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM recovery WHERE code=?;");
            stmt.setString(1, code);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();

            //Grab the code from that recovery entry
            return rs.getInt("userid");
        }catch(SQLException e){
            throw e;
        }
    }
}
