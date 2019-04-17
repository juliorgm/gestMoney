package br.com.juliorgm.gestmoney.model;

import java.io.Serializable;

public class Planejamento implements Serializable {

    private String mId;

    public void setmId(String mId) {
        this.mId = mId;
    }

    private String mNome;
    private String mReserva;
    private String mDataInicio;
    private String mDataFim;

    public Planejamento(String nome, String reserva, String dataInicio, String dataFim) {
    }

    public Planejamento(String mId, String mNome, String mReserva, String mDataInicio, String mDataFim) {
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

    public String getmReserva() {
        return mReserva;
    }

    public void setmReserva(String mReserva) {
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
