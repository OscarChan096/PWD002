package com.softchan.pwd.dbroom;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.softchan.pwd.dbroom.daos.CardDAO;
import com.softchan.pwd.dbroom.daos.PswdDAO;

import java.util.List;

public class DBAcces {

    private static DBAcces dbAcces;
    private PswdDAO pswdDAO;
    private CardDAO cardDAO;
    private AppDataBase appDataBase;

    // - - - -  - - - - -  - - - pruebas de migracion - - - - - - -  - - - - - -

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `card` (`id` INTEGER NOT NULL, "
                    + "`banco` TEXT, "
                    + "`numCuenta` INTEGER NOT NULL, "
                    + "`nip` INTEGER NOT NULL, "
                    + "`fecha` TEXT, "
                    + "`cvv` INTEGER NOT NULL, "
                    + "`usuarioApp` TEXT, "
                    + "`passwordApp` TEXT, "
                    + "`tarjetaVirtual` INTEGER NOT NULL, "
                    + "PRIMARY KEY(`id`))");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE card "
                    + " ADD COLUMN numeroDeCuenta TEXT");
        }
    };

    private DBAcces (Context context){
        Context appContext = context.getApplicationContext();
        appDataBase = Room.databaseBuilder(appContext, AppDataBase.class, "dbpswd").allowMainThreadQueries().addMigrations(MIGRATION_1_2,MIGRATION_2_3).build();
        pswdDAO = appDataBase.getDAO();
        cardDAO = appDataBase.getCardDAO();
    }

    public static DBAcces getInstance(Context context){
        if (dbAcces == null){
            dbAcces = new DBAcces(context);
        }
        return dbAcces;
    }

    // - - - - -- - - - - - - Contraseñas - - - - - - - - - - -

    public List<Pswd> getPswd(){
        return pswdDAO.getPswd();
    }

    public List<Pswd> findByTitulo(String titulo){
        return pswdDAO.findByTitulo(titulo);
    }

    public List<Pswd> findByUsuario(String usuario){
        return pswdDAO.findByUsuario(usuario);
    }

    public void add(Pswd pswd){
        pswdDAO.add(pswd);
    }

    public void delete(Pswd pswd){
        pswdDAO.delete(pswd);
    }

    public void deleteById(int id){
        pswdDAO.deleteById(id);
    }

    public void update(int id, String titulo, String usuario, String password){
        pswdDAO.update(id,titulo,usuario,password);
    }

    public void update(Pswd pswd){
        pswdDAO.updatex(pswd);
    }

   // - - - - - - - - - - - - - tarjetas - - - - - - - - - - -- -

    public List<Card> getCards(){return cardDAO.getCards();}

    public void addCard(Card card){cardDAO.add(card);}

    public void deleteCard(Card card){cardDAO.delete(card);}

    public void updateCard(int id, String banco, int numCuenta, int nip,
                           String fecha, int cvv, String usuarioApp, String passwordApp, int tarjetaVirtual){
        cardDAO.update(id,banco,numCuenta,nip,fecha,cvv,usuarioApp,passwordApp,tarjetaVirtual);
    }

    public void updateCard(int id, String banco, String numCuenta, int nip,
                           String fecha, int cvv, String usuarioApp, String passwordApp, int tarjetaVirtual){
        cardDAO.update(id,banco,numCuenta,nip,fecha,cvv,usuarioApp,passwordApp,tarjetaVirtual);
    }

}
