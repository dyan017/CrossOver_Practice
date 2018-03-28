package com.levo017.crossoverpractice.persistence.relationships;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Relation;

import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.models.User;

import java.util.List;

/**
 * Created by dyan017 on 3/27/2018.
 */

public class TopicsProposedByUser {
    @Embedded
    User user;

    @Relation(parentColumn = "Id", entityColumn ="UserId")
    List<Topic> topics;
}
