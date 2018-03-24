package com.levo017.crossoverpractice.persistence.relationships;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Relation;

import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.User;

import java.util.List;

/**
 * Created by dyan017 on 1/11/2018.
 */

public class SpeakerSessionInUser {
    @Embedded
    User user;

    @Relation(parentColumn = "Id", entityColumn ="UserId")
    List<Session> sessions;
}
