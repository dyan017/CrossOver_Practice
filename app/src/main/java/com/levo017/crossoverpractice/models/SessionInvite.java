package com.levo017.crossoverpractice.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by dyan017 on 1/10/2018.
 */

public class SessionInvite {
    @Embedded
    Session session;

    @Relation(entityColumn="SessionId", parentColumn ="Id" )
    List<Invite> invite;
}
