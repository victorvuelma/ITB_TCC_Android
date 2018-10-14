package br.com.burgershack.android.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.burgershack.android.object.Product;

public class DataLocal {

    private SQLiteDatabase _sqLiteDatabase;

    public void setup(){
        _sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase("burgershack.db", null);
        _sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PRODUCTS (CODIGO INTEGER NOT NULL, NOME VARCHAR(200) NOT NULL, DESCRICAO VARCHAR(512) NOT NULL, VALOR DOUBLE NOT NULL, TIPO INT NOT NULL)");
        _sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS USER_INFO (INFO_KEY VARCHAR(200) NOT NULL, INFO_VAL VARCHAR(200) NOT NULL)");
        _sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS USER_BOOKINGS (CODIGO INTEGER NOT NULL, DATA DATE NOT NULL, LUGARES INTEGER NOT NULL)");
    }

    public List<Product> getProducts(int tipo){
        List<Product> products = new ArrayList<>();


       Cursor sqlCur =  _sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTS WHERE TIPO = ?", new String[]{String.valueOf(tipo)});
       while (sqlCur.moveToNext()){

       }
       sqlCur.close();

       return  products;
    }

}
