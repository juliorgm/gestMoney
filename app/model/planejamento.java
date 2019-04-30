package model;

import java.io.Serializable;

public class planejamento implements Serializable{

      private String mId;
      public void setmId(String mId) {
            this.mId = mId;
        }

        private String mDescricao;
        private String mValorReserva;
        private String mDtInicio;
        private String mDtFim;

    public planejamento(String mId, String mDescricao, String mValorReserva, String mDtInicio, String mDtFim) {
        this.mId = mId;
        this.mDescricao = mDescricao;
        this.mValorReserva = mValorReserva;
        this.mDtInicio = mDtInicio;
        this.mDtFim = mDtFim;
    }
}
