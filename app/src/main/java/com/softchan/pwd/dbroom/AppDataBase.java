package com.softchan.pwd.dbroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.softchan.pwd.dbroom.daos.PswdDAO;

@Database(entities = {Pswd.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract PswdDAO getDAO();

}
