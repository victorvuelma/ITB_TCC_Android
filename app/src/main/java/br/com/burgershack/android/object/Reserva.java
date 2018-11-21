package br.com.burgershack.android.object;

import java.util.Date;

public class Reserva {

    private int _codigo;
    private Date _data;
    private int _lugares;
    private String _informacoes;
    private ReservaSituacao _situacao;

    public Reserva(int codigo, Date data, int lugares, String informacoes, ReservaSituacao situacao) {
        this._codigo = codigo;
        this._data = data;
        this._lugares = lugares;
        this._informacoes = informacoes;
        this._situacao = situacao;
    }

    public int getCodigo() {
        return _codigo;
    }

    public Date getData() {
        return _data;
    }

    public int getLugares() {
        return _lugares;
    }

    public String getInformacoes() {
        return _informacoes;
    }

    public ReservaSituacao getSituacao() {
        return _situacao;
    }

    public enum ReservaSituacao {

        MARCADA(),
        UTILIZADA(),
        CANCELADA(),
        CONFIRMADA();

        public static ReservaSituacao situacao(String prefixo)
        {
            switch (prefixo)
            {
                case "A":
                    return ReservaSituacao.CANCELADA;
                case "O":
                    return ReservaSituacao.CONFIRMADA;
                case "M":
                    return ReservaSituacao.MARCADA;
                default:
                    return ReservaSituacao.UTILIZADA;
            }
        }


    }

}
