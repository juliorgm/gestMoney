package br.com.juliorgm.gestmoney.models;

import java.io.Serializable;

public class Gastos implements Serializable {
    private int midGasto;
    private double mvalor;
    private String mdata;
    private String mcategoria;
    private String mdescricao;

    public void setmidGasto(int midGasto){
        this.midGasto = midGasto;
    }

    public Gastos(int midGasto, double mvalor, String mdata, String mcategoria, String mdescricao) {
        this.midGasto = midGasto;
        this.mvalor = mvalor;
        this.mdata = mdata;
        this.mcategoria = mcategoria;
        this.mdescricao = mdescricao;
    }

    public Gastos(double mvalor, String mdata, String mcategoria, String mdescricao){
        this.mvalor = mvalor;
        this.mdata = mdata;
        this.mcategoria = mcategoria;
        this.mdescricao = mdescricao;
    }

    public int getmIdGasto(){
        return midGasto;
    }

    public double getmValor() {
        return mvalor;
    }

    public String getmValorToString() {
        return String.valueOf(mvalor);
    }

    public String getmData() {
        return mdata;
    }

    public String getmCategoria() {
        return mcategoria;
    }

    public String getmDescricao() {
        return mdescricao;
    }

}
