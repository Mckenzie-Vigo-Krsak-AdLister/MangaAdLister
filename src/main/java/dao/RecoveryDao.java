package dao;

import java.sql.SQLException;

public interface RecoveryDao {

    String getRecoverCodeForUserId(int userid) throws SQLException;
    int getUserIdForCode(String code) throws SQLException;

}
