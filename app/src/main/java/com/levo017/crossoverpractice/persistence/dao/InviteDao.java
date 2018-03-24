package com.levo017.crossoverpractice.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.levo017.crossoverpractice.models.Invite;

/**
 * Created by dyan017 on 1/11/2018.
 */
@Dao
public interface InviteDao {
    @Insert
    void InsertInvite(Invite invite);

    @Delete
    void DeleteInvite(Invite invite);

    @Update
    void UpdateInvite(Invite invite);
}
