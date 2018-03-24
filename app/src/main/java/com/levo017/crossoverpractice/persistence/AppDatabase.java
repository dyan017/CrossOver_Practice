package com.levo017.crossoverpractice.persistence;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Invite;
import com.levo017.crossoverpractice.models.Session;
import com.levo017.crossoverpractice.models.Topic;
import com.levo017.crossoverpractice.models.User;
import com.levo017.crossoverpractice.models.converters.UserTypeConverter;
import com.levo017.crossoverpractice.persistence.dao.ConferenceDao;
import com.levo017.crossoverpractice.persistence.dao.InviteDao;
import com.levo017.crossoverpractice.persistence.dao.SessionDao;
import com.levo017.crossoverpractice.persistence.dao.TopicDao;
import com.levo017.crossoverpractice.persistence.dao.UserDao;
import com.levo017.crossoverpractice.persistence.relationships.ConferenceTopicRelationship;
//import com.levo017.crossoverpractice.persistence.relationships.SessionsInConference;

/**
 * Created by dyan017 on 1/9/2018.
 */
@Database(entities = {Conference.class, Session.class, Topic.class, ConferenceTopicRelationship.class, Invite.class, User.class}, version = 1)
@TypeConverters({UserTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String DB_NAME="OctopusClient.db";

    public abstract ConferenceDao conferenceDao();
    public abstract InviteDao inviteDao();
    public abstract SessionDao sessionDao();
    public abstract TopicDao topicDao();
    public abstract UserDao userDao();

    public static AppDatabase create(Context context, boolean memoryOnly){
        RoomDatabase.Builder<AppDatabase> b;
        if (memoryOnly){
            b = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class);
        } else {
            b = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME);
        }
        return (b.build());
    }

    public static AppDatabase createForTest(Context context, boolean memoryOnly){
        RoomDatabase.Builder<AppDatabase> b;
        if (memoryOnly){
            b = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).allowMainThreadQueries();
        } else {
            b = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME).allowMainThreadQueries();
        }
        return (b.build());
    }
}
