package br.com.juliorgm.gestmoney.model;

import android.content.Context;

import java.io.Serializable;

import br.com.juliorgm.gestmoney.planejamento.FormularioPlanejamentoFragment;

public class Planejamento implements Serializable {

    private String mId;

    public void setmId(String mId) {
        this.mId = mId;
    }

    private String mNome;
    private double mReserva;
    private String mDataInicio;
    private String mDataFim;

    public Planejamento() {
    }

    public Planejamento(String mNome, double mReserva, String mDataInicio, String mDataFim) {
        this.mNome = mNome;
        this.mReserva = mReserva;
        this.mDataInicio = mDataInicio;
        this.mDataFim = mDataFim;
    }

    public Planejamento(String mId, String mNome, Double mReserva, String mDataInicio, String mDataFim) {
        this.mId = mId;
        this.mNome = mNome;
        this.mReserva = mReserva;
        this.mDataInicio = mDataInicio;
        this.mDataFim = mDataFim;
    }

    public String getmId() {
        return mId;
    }

    public String getmNome() {
        return mNome;
    }

    public void setmNome(String mNome) {
        this.mNome = mNome;
    }

    public double getmReserva() {
        return mReserva;
    }

    public void setmReserva(Double mReserva) {
        this.mReserva = mReserva;
    }

    public String getmDataInicio() {
        return mDataInicio;
    }

    public void setmDataInicio(String mDataInicio) {
        this.mDataInicio = mDataInicio;
    }

    public String getmDataFim() {
        return mDataFim;
    }

    public void setmDataFim(String mDataFim) {
        this.mDataFim = mDataFim;
    }

}
