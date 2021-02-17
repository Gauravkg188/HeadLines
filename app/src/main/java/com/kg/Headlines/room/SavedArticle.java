package com.kg.Headlines.room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName= "Article",indices = {@Index(value = {"title"},unique = true)})

public class SavedArticle {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String author;
    @ColumnInfo(name = "title")
    private String title;
    private String date;
    private String url;
    private String imageUrl;

    public SavedArticle(String author, String title, String date, String url,String imageUrl) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.url = url;
        this.imageUrl=imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
