/*
    Created by IntelliJ IDEA.
    User: aldanisvigo
    Date: 1/8/23
    Time: 6:01 AM
*/
package models;

import java.io.Serializable;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Serializable {
    private int id;
    private String email;
    private String password;

    private String first_name;

    private String last_name;
    private Date created;

    private String roles;

    public User(int id, String email, String password,String firstName,String lastName, Date created,String roles){
        this.id = id;
        this.email = email;
        this.password = password;
        this.created = created;
        this.roles = roles;
        this.first_name = firstName;
        this.last_name = lastName;
    }

    public User(String email, String password,String firstName, String lastName, Date created, String roles){
        this.email = email;
        this.password = password;
        this.created = created;
        this.roles = roles;
        this.first_name = firstName;
        this.last_name = lastName;
    }

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRoles() { return roles; }

    public void setRoles(String roles){
        this.roles = roles;
    }

    public static User userFromResultSet(ResultSet rs) throws SQLException {
        try {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Date created = rs.getDate("created");
            String roles = rs.getString("roles");

            User usr = new User();
            usr.setId(id);
            usr.setEmail(email);
            usr.setFirstName(firstName);
            usr.setLast_name(lastName);
            usr.setPassword(password);
            usr.setCreated(created);
            usr.setRoles(roles);

            return usr;

        } catch (SQLException e){
            throw e;
        }
    }

    public String getLastName() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public void setFirstName(String first_name){
        this.first_name = first_name;
    }

}
