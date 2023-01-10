package models;

import java.io.Serializable;

public class Recovery implements Serializable {
    private int id;
    private String code;
    private int userid;

    public Recovery(int id, String code, int userid){
        this.id = id;
        this.code = code;
        this.userid = userid;
    }

    public Recovery(String code, int userid){
        this.code = code;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
