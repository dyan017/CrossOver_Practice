package com.levo017.crossoverpractice.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by dyan017 on 1/10/2018.
 */

@Entity
public class Session {
    @PrimaryKey(autoGenerate = true)
    public Integer Id;
    public String ConferenceId;
    public String TopicId;
    public String UserId;
    public String InviteId;
}
