package com.antospa.rest.item;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Document
public class Item implements Serializable {
    @Id
    private String id;
    private Integer value;
    private String tag;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatedAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date expireAt;

    public Item(){}

    public Item(String id, Integer value, String tag, Date updatedAt, Date expireAt) {
        this.id = id;
        this.value = value;
        this.tag = tag;
        this.updatedAt = updatedAt;
        this.expireAt = expireAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", tag='" + tag + '\'' +
                ", updatedAt=" + updatedAt +
                ", expireAt=" + expireAt +
                '}';
    }
}
