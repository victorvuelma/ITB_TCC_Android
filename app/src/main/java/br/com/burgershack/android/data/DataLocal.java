package br.com.burgershack.android.data;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.burgershack.android.object.Product;

public class DataLocal {

    private Activity _activity;

    public DataLocal(Activity activity){
        this._activity = activity;
    }

    public void setup(){
        SQLiteDatabase database = getDatabase();
        database.execSQL("CREATE TABLE IF NOT EXISTS PRODUCTS (CODIGO INTEGER NOT NULL, NOME VARCHAR(200) NOT NULL, DESCRICAO VARCHAR(512) NOT NULL, VALOR DOUBLE NOT NULL, TIPO INT NOT NULL)");
        database.execSQL("CREATE TABLE IF NOT EXISTS USER_INFO (INFO_KEY VARCHAR(200) NOT NULL, INFO_VAL VARCHAR(200) NOT NULL)");
        database.execSQL("CREATE TABLE IF NOT EXISTS USER_BOOKINGS (CODIGO INTEGER NOT NULL, DATA DATE NOT NULL, LUGARES INTEGER NOT NULL, INFORMACOES VARCHAR(200) NOT NULL)");
    }

    public List<Product> produtosObter(int tipo){
        List<Product> products = new ArrayList<>();

       Cursor sqlCur =  getDatabase().rawQuery("SELECT * FROM PRODUCTS WHERE TIPO = ?", new String[]{String.valueOf(tipo)});
       while (sqlCur.moveToNext()){

       }
       sqlCur.close();

       return  products;
    }

    public SQLiteDatabase getDatabase(){
        return  _activity.openOrCreateDatabase("burgershack.db", Context.MODE_PRIVATE, null);
    }

    public void produtosLimpar() {
    }
}
