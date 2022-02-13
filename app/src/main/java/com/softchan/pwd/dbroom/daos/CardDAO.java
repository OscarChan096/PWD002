package com.softchan.pwd.dbroom.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.softchan.pwd.dbroom.Card;
import com.softchan.pwd.dbroom.Pswd;

import java.util.List;

@Dao
public interface CardDAO {

    @Query("SELECT * FROM card")
    List<Card> getCards();

    @Query("SELECT * FROM card WHERE banco LIKE :banco")
    Card findByBanco(String banco);

    @Insert
    void add(Card card);

    @Delete
    void delete(Card card);

    @Query("DELETE FROM card WHERE id=:id")
    void delete(int id);

    @Query("UPDATE card SET banco=:banco, numCuenta=:numCuenta, nip=:nip, fecha=:fecha, cvv=:cvv, usuarioApp=:usuarioApp, passwordApp=:passwordApp, tarjetaVirtual=:tarjetaVirtual WHERE id=:id")
    void update(int id, String banco, int numCuenta, int nip, String fecha,
                int cvv, String usuarioApp, String passwordApp, int tarjetaVirtual);

    @Query("UPDATE card SET banco=:banco, numeroDeCuenta=:numueroDeCuenta, nip=:nip, fecha=:fecha, cvv=:cvv, usuarioApp=:usuarioApp, passwordApp=:passwordApp, tarjetaVirtual=:tarjetaVirtual WHERE id=:id")
    void update(int id, String banco, String numueroDeCuenta, int nip, String fecha,
                int cvv, String usuarioApp, String passwordApp, int tarjetaVirtual);

    @Update
    void update(Card card);

}
