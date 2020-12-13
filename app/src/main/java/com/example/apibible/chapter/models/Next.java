package com.example.apibible.chapter.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Next {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("bookId")
    @Expose
    private String bookId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

}
