package br.com.juliorgm.gestmoney.models;

import java.io.Serializable;

public class Gastos implements Serializable {
    private int mIdGasto;
    private double mValor;
    private String mData;
    private String mCategoria;
    private String mDescricao;

    public Gastos(int mIdGasto, double mValor, String mData, String mCategoria, String mDescricao) {
        this.mIdGasto = mIdGasto;
        this.mValor = mValor;
        this.mData = mData;
        this.mCategoria = mCategoria;
        this.mDescricao = mDescricao;
    }

    public Gastos(double mValor, String mData, String mCategoria, String mDescricao){
        this.mValor = mValor;
        this.mData = mData;
        this.mCategoria = mCategoria;
        this.mDescricao = mDescricao;
    }

    public int getmIdGasto(){
        return mIdGasto;
    }

    public double getmValor() {
        return mValor;
    }

    public String pegaValorTexto() {
        return String.valueOf(mValor);
    }

    public String getmData() {
        return mData;
    }

    public String getmCategoria() {
        return mCategoria;
    }

    public String getmDescricao() {
        return mDescricao;
    }

}
