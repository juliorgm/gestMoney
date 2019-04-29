package br.com.juliorgm.gestmoney.model;

import android.content.Context;

import java.io.Serializable;

import br.com.juliorgm.gestmoney.receita.FormularioReceitaFragment;

public class Receita implements Serializable {

    private String mId;
    private String mDescricao;
    private double mValor;
    private String mData;

    public Receita() {

    }

    public Receita(String descricao, double valor, String data) {
        this.mDescricao = descricao;
        this.mValor = valor;
        this.mData = data;
    }


    public Receita(String mId, String descricao, double valor, String data) {
        this.mId = mId;
        this.mDescricao = descricao;
        this.mValor = valor;
        this.mData = data;
    }


    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmDescricao() {
        return mDescricao;
    }

    public void setmDescricao(String mDescricao) {
        this.mDescricao = mDescricao;
    }

    public double getmValor() {
        return mValor;
    }

    public void setmValor(double mValor) {
        this.mValor = mValor;
    }

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }

    public void getValue(Context mContext, Class<FormularioReceitaFragment> formularioReceitaFragmentClass) {
    }
}


