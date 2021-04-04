package com.softchan.pwd.dbroom.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.softchan.pwd.dbroom.Pswd;

import java.util.List;

@Dao
public interface PswdDAO {

    @Query("SELECT * FROM pswd")
    List<Pswd> getPswd();

    @Query("SELECT * FROM pswd WHERE titulo LIKE :titulo")
    List<Pswd> findByTitulo(String titulo);

    @Query("SELECT * FROM pswd WHERE usuario LIKE :usuario")
    List<Pswd> findByUsuario(String usuario);

    @Insert
    void add(Pswd pswd);

    @Delete
    void delete(Pswd pswd);

    @Query("DELETE FROM pswd WHERE id=:id")
    void deleteById(int id);

    @Query("UPDATE pswd SET titulo=:titulo, usuario=:usuario, password=:password WHERE id=:id")
    void update(int id, String titulo, String usuario, String password);

    @Update
    void updatex(Pswd pswd);

}
