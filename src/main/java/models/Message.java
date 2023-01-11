package models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {

    private long id;
    private String message;
    private Timestamp time_stamp;
    private long fromId;
    private long toId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Timestamp time_stamp) {
        this.time_stamp = time_stamp;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public Message(String message, Timestamp time_stamp, long fromId, long toId) {
        this.message = message;
        this.time_stamp = time_stamp;
        this.fromId = fromId;
        this.toId = toId;
    }

    public Message(long id, String message, Timestamp time_stamp, long fromId, long toId) {
        this.id = id;
        this.message = message;
        this.time_stamp = time_stamp;
        this.fromId = fromId;
        this.toId = toId;
    }

}
