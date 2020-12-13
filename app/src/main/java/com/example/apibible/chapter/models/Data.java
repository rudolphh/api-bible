
package com.example.apibible.chapter.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("bibleId")
    @Expose
    private String bibleId;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("bookId")
    @Expose
    private String bookId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("verseCount")
    @Expose
    private int verseCount;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("next")
    @Expose
    private Next next;
    @SerializedName("previous")
    @Expose
    private Previous previous;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBibleId() {
        return bibleId;
    }

    public void setBibleId(String bibleId) {
        this.bibleId = bibleId;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getVerseCount() {
        return verseCount;
    }

    public void setVerseCount(int verseCount) {
        this.verseCount = verseCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Next getNext() {
        return next;
    }

    public void setNext(Next next) {
        this.next = next;
    }

    public Previous getPrevious() {
        return previous;
    }

    public void setPrevious(Previous previous) {
        this.previous = previous;
    }

}
