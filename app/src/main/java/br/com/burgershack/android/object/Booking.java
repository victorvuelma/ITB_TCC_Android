package br.com.burgershack.android.object;

import java.util.Date;

public class Booking {

    private int codigo;
    private Date data;
    private int pessoas;
    private String informacoes;

    public Booking(int codigo, Date data, int pessoas, String informacoes){
        this.codigo = codigo;
        this.data = data;
        this.pessoas = pessoas;
        this.informacoes = informacoes;
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getData() {
        return data;
    }

    public int getPessoas() {
        return pessoas;
    }

    public String getInformacoes() {
        return informacoes;
    }
}
