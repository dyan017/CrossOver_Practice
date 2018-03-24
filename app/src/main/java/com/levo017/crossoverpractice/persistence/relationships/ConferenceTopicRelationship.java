package com.levo017.crossoverpractice.persistence.relationships;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Topic;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by dyan017 on 1/10/2018.
 */

@Entity(
        tableName = "conference_topic_join",
        primaryKeys = {"conferenceId", "topicId"},
        foreignKeys = {
                @ForeignKey(
                        entity = Conference.class,
                        parentColumns = "ConferenceId",
                        childColumns = "conferenceId",
                        onDelete=CASCADE
                ),
                @ForeignKey(
                        entity = Topic.class,
                        parentColumns = "TopicId",
                        childColumns = "topicId",
                        onDelete=CASCADE
                )
        }
)
public class ConferenceTopicRelationship {
    @NonNull
    public final String conferenceId;

    @NonNull
    public final String topicId;

    public ConferenceTopicRelationship(String conferenceId, String topicId){
        this.conferenceId = conferenceId;
        this.topicId = topicId;
    }
}
