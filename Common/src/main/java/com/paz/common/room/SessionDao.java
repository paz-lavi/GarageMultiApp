package com.paz.common.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.paz.common.model.Session;

import java.util.List;

@Dao
public interface SessionDao {
    @Query("SELECT * FROM session")
    List<Session> getAll();
    @Insert
    void insertAll(Session... sessions);
    @Insert
    void insert(Session session);



    @Query("SELECT * FROM session WHERE start = (SELECT MAX(start) FROM session)")
    Session getLastSession();

    @Delete
    void delete(Session session);

    @Query("SELECT SUM(total) FROM session")
    long totalSpendsTime();
}
