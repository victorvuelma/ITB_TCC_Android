package br.com.burgershack.android.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.burgershack.android.object.Produto;
import br.com.burgershack.android.object.Reserva;

public class LocalData {

    public static final String PRODUTOS_TABELA = "PRODUTOS";
    public static final String PRODUTOS_CODIGO = "CODIGO";
    public static final String PRODUTOS_NOME = "NOME";
    public static final String PRODUTOS_DESCRICAO = "DESCRICAO";
    public static final String PRODUTOS_VALOR = "VALOR";
    public static final String PRODUTOS_TIPO = "TIPO";
    public static final String PRODUTOS_IMAGEM = "IMAGEM";

    public static final String CLIENTE_TABELA = "CLIENTE_INFO";
    public static final String CLIENTE_KEY = "INFO_KEY";
    public static final String CLIENTE_VAL = "INFO_VAL";
    public static final String CLIENTE_COD = "COD";
    public static final String CLIENTE_NOME = "NOME";
    public static final String CLIENTE_CPF = "CPF";
    public static final String CLIENTE_EMAIL = "EMAIL";
    public static final String CLIENTE_CELULAR = "CELULAR";
    public static final String CLIENTE_CADASTRO = "CADASTRO";

    public static final String RESERVAS_TABELA = "RESERVAS";
    public static final String RESERVAS_CODIGO = "CODIGO";
    public static final String RESERVAS_LUGARES = "LUGARES";
    public static final String RESERVAS_INFORMACOES = "INFORMACOES";
    public static final String RESERVAS_SITUACAO = "SITUACAO";
    public static final String RESERVAS_DATA = "DATA";

    private LocalDatabase _localDB;

    public LocalData(Context context) {
        this._localDB = new LocalDatabase(context);
    }

    public List<Produto> produtosObter(int tipo) {
        List<Produto> produtos = new ArrayList<>();

        SQLiteDatabase db = _localDB.getReadableDatabase();
        String[] tables = {PRODUTOS_CODIGO, PRODUTOS_NOME, PRODUTOS_DESCRICAO,
                PRODUTOS_VALOR, PRODUTOS_IMAGEM};
        String[] where = {String.valueOf(tipo)};
        Cursor cursor = db.query(PRODUTOS_TABELA, tables, PRODUTOS_TIPO + "=?", where, null, null, PRODUTOS_NOME);
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(cursor.getColumnIndex(PRODUTOS_CODIGO));
                String nome = cursor.getString(cursor.getColumnIndex(PRODUTOS_NOME));
                String descricao = cursor.getString(cursor.getColumnIndex(PRODUTOS_DESCRICAO));
                double valor = cursor.getDouble(cursor.getColumnIndex(PRODUTOS_VALOR));
                byte[] imagem = cursor.getBlob(cursor.getColumnIndex(PRODUTOS_IMAGEM));

                Produto produto = new Produto(codigo, nome, descricao, valor, tipo, imagem);
                produtos.add(produto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return produtos;
    }

    public void produtosInserir(int codigo, String nome, String descricao, double valor, int tipo, byte[] imagem) {
        ContentValues cv = new ContentValues();
        cv.put(PRODUTOS_CODIGO, codigo);
        cv.put(PRODUTOS_NOME, nome);
        cv.put(PRODUTOS_DESCRICAO, descricao);
        cv.put(PRODUTOS_VALOR, valor);
        cv.put(PRODUTOS_TIPO, tipo);
        cv.put(PRODUTOS_IMAGEM, imagem);

        SQLiteDatabase db = _localDB.getWritableDatabase();
        db.insert(PRODUTOS_TABELA, null, cv);
        db.close();
    }

    public void produtosLimpar() {
        SQLiteDatabase db = _localDB.getWritableDatabase();
        db.delete(PRODUTOS_TABELA, null, null);
        db.close();
    }

    public Produto produtoObter(int codigo) {
        Produto produto = null;

        SQLiteDatabase db = _localDB.getReadableDatabase();
        String[] tables = {PRODUTOS_CODIGO, PRODUTOS_NOME, PRODUTOS_DESCRICAO,
                PRODUTOS_VALOR, PRODUTOS_TIPO, PRODUTOS_IMAGEM};
        String[] where = {String.valueOf(codigo)};
        Cursor cursor = db.query(PRODUTOS_TABELA, tables, PRODUTOS_CODIGO + "=?", where, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndex(PRODUTOS_NOME));
                String descricao = cursor.getString(cursor.getColumnIndex(PRODUTOS_DESCRICAO));
                double valor = cursor.getDouble(cursor.getColumnIndex(PRODUTOS_VALOR));
                int tipo = cursor.getInt(cursor.getColumnIndex(PRODUTOS_TIPO));
                byte[] imagem = cursor.getBlob(cursor.getColumnIndex(PRODUTOS_IMAGEM));

                produto = new Produto(codigo, nome, descricao, valor, tipo, imagem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return produto;
    }

    public String clienteObter(String key) {
        String result = "";

        SQLiteDatabase db = _localDB.getReadableDatabase();
        String[] tables = {CLIENTE_VAL};
        String[] where = {key};
        Cursor cursor = db.query(CLIENTE_TABELA, tables, CLIENTE_KEY + "=?", where, null, null, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(CLIENTE_VAL));
        }
        cursor.close();
        db.close();

        return result;
    }

    public void clienteInserir(String key, String value) {
        ContentValues cv = new ContentValues();
        cv.put(CLIENTE_KEY, key);
        cv.put(CLIENTE_VAL, value);

        SQLiteDatabase db = _localDB.getWritableDatabase();
        db.insert(CLIENTE_TABELA, null, cv);
        db.close();
    }

    public void clienteLimpar() {
        SQLiteDatabase db = _localDB.getWritableDatabase();
        db.delete(CLIENTE_TABELA, null, null);
        db.close();
    }

    public boolean clienteLogado() {
        return !clienteObter(LocalData.CLIENTE_COD).isEmpty();
    }

    public List<Reserva> reservasObter() {
        List<Reserva> reservas = new ArrayList<>();

        SQLiteDatabase db = _localDB.getReadableDatabase();
        String[] tables = {RESERVAS_CODIGO, RESERVAS_DATA, RESERVAS_LUGARES,
                RESERVAS_INFORMACOES, RESERVAS_SITUACAO };
        Cursor cursor = db.query(RESERVAS_TABELA, tables, null, null, null, null, RESERVAS_DATA);
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(cursor.getColumnIndex(RESERVAS_CODIGO));
                Date data = new Date(cursor.getLong(cursor.getColumnIndex(RESERVAS_DATA)) * 1000);
                int lugares = cursor.getInt(cursor.getColumnIndex(RESERVAS_LUGARES));
                String informacoes = cursor.getString(cursor.getColumnIndex(RESERVAS_INFORMACOES));
                String situacao = cursor.getString(cursor.getColumnIndex(RESERVAS_SITUACAO));

                Reserva reserva = new Reserva(codigo, data, lugares, informacoes, Reserva.ReservaSituacao.situacao(situacao));
                reservas.add(reserva);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return reservas;
    }

    public void reservasLimpar() {
        SQLiteDatabase db = _localDB.getWritableDatabase();
        db.delete(RESERVAS_TABELA, null, null);
        db.close();
    }

    public void reservasInserir(int codigo, long data, int lugares, String informacoes, String situacao) {
        ContentValues cv = new ContentValues();
        cv.put(RESERVAS_CODIGO, codigo);
        cv.put(RESERVAS_DATA, data);
        cv.put(RESERVAS_LUGARES, lugares);
        cv.put(RESERVAS_INFORMACOES, informacoes);
        cv.put(RESERVAS_SITUACAO, situacao);

        SQLiteDatabase db = _localDB.getWritableDatabase();
        db.insert(RESERVAS_TABELA, null, cv);
        db.close();
    }
}
