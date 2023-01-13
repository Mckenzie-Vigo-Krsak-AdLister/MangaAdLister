package models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Cart implements Serializable {
    private int id;
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    private int userid;
    public Timestamp getCreated() {
        return created;
    }
    public void setCreated(Timestamp created) {
        this.created = created;
    }
    private Timestamp created;
    public Cart(int id, int userid, Timestamp created){
        this.id = id;
        this.userid = userid;
        this.created = created;
    }

    public int getId() {
        return this.id;
    }
    public int getUserId() {
        return this.userid;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setUserId(int userid){
        this.userid = userid;
    }
}
