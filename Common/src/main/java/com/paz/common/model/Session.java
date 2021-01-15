package com.paz.common.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Session {
    @ColumnInfo(name = "start")
    private long start;

    @ColumnInfo(name = "end")
    private long end;

    @ColumnInfo(name = "total")
    private long total;

    @PrimaryKey(autoGenerate = true)
    private long id;

}
