package com.softchan.pwd.dbroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.softchan.pwd.dbroom.daos.CardDAO;
import com.softchan.pwd.dbroom.daos.PswdDAO;

@Database(entities = {Pswd.class, Card.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PswdDAO getDAO();
    public abstract CardDAO getCardDAO();

}
