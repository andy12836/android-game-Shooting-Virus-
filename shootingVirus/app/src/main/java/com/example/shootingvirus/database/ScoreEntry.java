package com.example.shootingvirus.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TimePicker;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "score")
public class ScoreEntry implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int score;
    private int playTime;

    public String getRecordTime() {
        return recordTime;
    }

    private String recordTime;

    public ScoreEntry(int id, int score, int playTime, String recordTime) {
        this.id = id;
        this.score = score;
        this.playTime = playTime;
        this.recordTime = recordTime;
    }

    @Ignore
    public ScoreEntry(int score, int playTime, String recordTime) {
        this.score = score;
        this.playTime = playTime;
        this.recordTime = recordTime;
    }

    protected ScoreEntry(Parcel in) {
        id = in.readInt();
        score = in.readInt();
        playTime = in.readInt();
        recordTime = in.readString();
    }

    public static final Creator<ScoreEntry> CREATOR = new Creator<ScoreEntry>() {
        @Override
        public ScoreEntry createFromParcel(Parcel in) {
            return new ScoreEntry(in);
        }

        @Override
        public ScoreEntry[] newArray(int size) {
            return new ScoreEntry[size];
        }
    };

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(score);
        dest.writeInt(playTime);
        dest.writeString(recordTime);
    }
}
