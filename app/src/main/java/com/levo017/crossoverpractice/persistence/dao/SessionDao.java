package com.levo017.crossoverpractice.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.levo017.crossoverpractice.models.Conference;
import com.levo017.crossoverpractice.models.Session;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by dyan017 on 1/11/2018.
 */

@Dao
public abstract class SessionDao {
    @Insert
    public abstract long insertSession(Session session);

    @Delete
    public abstract void deleteSession(Session session);

    @Update
    public abstract void updateSession(Session session);

    @Query("SELECT * FROM Session WHERE Session.Id == :SessionId")
    public abstract Maybe<Session> querySession(int SessionId);

    @Query("SELECT * FROM Session")
    public abstract Flowable<Session> querySessions();

    public Completable AssignUserToSession(int userId, int SessionId){
        try{
            return doAssignUserToSession(userId, SessionId);
        } catch (Exception ex){
            return Completable.error(ex);
        }
    }

    @Transaction
    protected Completable doAssignUserToSession(int userId, int SessionId){
        querySession(SessionId)
                .doOnSuccess(session -> {
                       session.UserId = userId;
                       updateSession(session);
                        }
                )
                .doOnComplete(()->{
                        Session session = new Session();
                        session.UserId = userId;
                        insertSession(session);
                        }
                );

        return Completable.complete();
    }
}
