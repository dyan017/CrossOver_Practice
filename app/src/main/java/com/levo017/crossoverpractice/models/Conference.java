package com.levo017.crossoverpractice.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by dyan017 on 1/8/2018.
 */

@Entity
public class Conference {
    @PrimaryKey(autoGenerate = true) @NonNull
    public int ConferenceId;

    public String ConferenceName;
}
