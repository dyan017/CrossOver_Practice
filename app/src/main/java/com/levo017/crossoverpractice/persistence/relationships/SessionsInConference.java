package com.levo017.crossoverpractice.persistence.relationships;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Relation;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by dyan017 on 1/10/2018.
 */
public class SessionsInConference {
    @Embedded
    public Conference conference;

    @Relation(parentColumn = "ConferenceId", entityColumn ="Id")
    public List<Session> sessions;
}
