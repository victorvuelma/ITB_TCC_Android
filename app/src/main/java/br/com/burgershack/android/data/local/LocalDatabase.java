package br.com.burgershack.android.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class LocalDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "burgershack";
    private static final int DATABASE_VERSION = 4;

    public LocalDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + LocalData.PRODUTOS_TABELA + " (" +
                LocalData.PRODUTOS_CODIGO + " INTEGER NOT NULL PRIMARY KEY, " +
                LocalData.PRODUTOS_NOME + " VARCHAR(100) NOT NULL, " +
                LocalData.PRODUTOS_DESCRICAO + " TEXT NOT NULL, " +
                LocalData.PRODUTOS_VALOR + " DOUBLE NOT NULL, " +
                LocalData.PRODUTOS_TIPO + " INT NOT NULL, " +
                LocalData.PRODUTOS_IMAGEM + " BLOB NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + LocalData.CLIENTE_TABELA + " ("
                + LocalData.CLIENTE_KEY + " VARCHAR(64) NOT NULL PRIMARY KEY, " +
                LocalData.CLIENTE_VAL + " VARCHAR(256) NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + LocalData.RESERVAS_TABELA + " (" +
                LocalData.RESERVAS_CODIGO + " INTEGER NOT NULL PRIMARY KEY, " +
                LocalData.RESERVAS_DATA +" LONG NOT NULL, " +
                LocalData.RESERVAS_LUGARES + " INTEGER NOT NULL, " +
                LocalData.RESERVAS_INFORMACOES + " VARCHAR(200) NOT NULL," +
                LocalData.RESERVAS_SITUACAO + " CHAR(1) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LocalData.PRODUTOS_TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + LocalData.CLIENTE_TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + LocalData.RESERVAS_TABELA);
        onCreate(db);
    }
}
