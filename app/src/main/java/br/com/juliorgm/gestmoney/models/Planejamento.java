package br.com.juliorgm.gestmoney.models;

public class Planejamento {
    private String mId;
    private String mNome;
    private String mValor;

    public Planejamento() {
    }

    public Planejamento(String mNome, String mValor) {
        this.mNome = mNome;
        this.mValor = mValor;
    }

    public String getmNome() {
        return mNome;
    }

    public String getmValor() {
        return mValor;
    }
}
