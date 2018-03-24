package com.levo017.crossoverpractice.persistence.relationships;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Relation;

import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;

import java.util.List;

/**
 * Created by dyan017 on 1/10/2018.
 */
@Entity
public class SessionsInTopic {
    @Embedded
    Topic topic;

    @Relation(parentColumn = "Id", entityColumn ="TopicId")
    List<Session> sessions;
}
