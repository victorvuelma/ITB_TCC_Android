package br.com.burgershack.android.object;

public class Product {

    private int _codigo;

    private String _nome;
    private String _imagem;
    private String _descricao;

    private double _valor;
    private int _tipo;

    public Product(int codigo, String nome, String imagem, String descricao, double valor, int tipo){
        this._codigo = codigo;
        this._nome = nome;
        this._imagem = imagem;
        this._descricao = descricao;
        this._valor = valor;
        this._tipo = tipo;
    }

    public int getCodigo() {
        return _codigo;
    }

    public String getNome() {
        return _nome;
    }

    public String getImagem() {
        return _imagem;
    }

    public String getDescricao() {
        return _descricao;
    }

    public double getValor() {
        return _valor;
    }

    public int getTipo() {
        return _tipo;
    }
}
