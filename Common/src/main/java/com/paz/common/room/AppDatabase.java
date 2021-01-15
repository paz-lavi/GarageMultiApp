package com.paz.common.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.paz.common.model.Session;

@Database(entities = {Session.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SessionDao sessionDao();
}