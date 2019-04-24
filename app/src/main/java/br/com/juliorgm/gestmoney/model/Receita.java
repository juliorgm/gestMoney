package br.com.juliorgm.gestmoney.model;

import android.content.Context;

import java.io.Serializable;

import br.com.juliorgm.gestmoney.receita.FormularioReceitaFragment;

public class Receita implements Serializable {

    private String mId;


    public void setmId(String mId) {
        this.mId = mId;
    }

    private String mdescricao;
    private double mvalor;
    private String mdata;

    public Receita(){

    }

    public Receita(String descricao, double valor, String data) {
        this.mdescricao = descricao;
        this.mvalor = valor;
        this.mdata = data;
    }


    public Receita(String mId, String descricao, double valor, String data) {
        this.mId = mId;
        this.mdescricao = descricao;
        this.mvalor = valor;
        this.mdata = data;
    }


    public String getmId() {
        return mId;
    }

    public String getMdescricao() {
        return mdescricao;
    }


    public void setMdescricao(String mdescricao) {
        this.mdescricao = mdescricao;
    }

    public double getMvalor() {
        return (int) mvalor;
    }


    public void setMvalor(double mvalor) {
        this.mvalor = mvalor;
    }


    public String getMdata() {
        return mdata;
    }

    public void setMdata(String mdata) {
        this.mdata = mdata;
    }


    public void getValue(Context mContext, Class<FormularioReceitaFragment> formularioReceitaFragmentClass) {
    }
}


