package com.kg.Headlines.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoInterface {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SavedArticle article);

    @Delete
    void delete(SavedArticle article);


    @Query("SELECT * FROM Article ORDER BY date ASC")
    LiveData<List<SavedArticle>> getAllArticles();

    @Query("DELETE FROM Article")
    void deleteAllArticles();

}
