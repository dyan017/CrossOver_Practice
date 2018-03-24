package com.levo017.crossoverpractice.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by dyan017 on 1/10/2018.
 */
@Entity
public class Invite {
    @PrimaryKey @NonNull
    public String InviteId;
    public String SessionId;
    public String UserId;
    public boolean Accepted;
}
