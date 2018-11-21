package br.com.burgershack.android.object;

public class Produto {

    private int _codigo;

    private String _nome;
    private String _descricao;

    private double _valor;
    private int _tipo;

    private byte[] _imagem;

    public Produto(int codigo, String nome, String descricao, double valor, int tipo, byte[] imagem) {
        this._codigo = codigo;
        this._nome = nome;
        this._descricao = descricao;
        this._valor = valor;
        this._tipo = tipo;
        this._imagem = imagem;
    }

    public int getCodigo() {
        return _codigo;
    }

    public String getNome() {
        return _nome;
    }

    public byte[] getImagem() {
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
