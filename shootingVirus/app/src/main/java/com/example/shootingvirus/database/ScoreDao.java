package com.example.shootingvirus.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface ScoreDao {

    @Query("SELECT * FROM score WHERE playTime = :playTime ORDER BY score DESC LIMIT 10 ")
    LiveData<List<ScoreEntry>> loadScoreByPlayTime(int playTime);

    @Insert
    void insertScore(ScoreEntry scoreEntry);

    @Delete
    void deleteScore(ScoreEntry scoreEntry);
}
