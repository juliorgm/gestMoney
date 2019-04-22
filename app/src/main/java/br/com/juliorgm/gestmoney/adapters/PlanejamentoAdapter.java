package br.com.juliorgm.gestmoney.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.models.Planejamento;

public class PlanejamentoAdapter extends RecyclerView.Adapter<PlanejamentoAdapter.PlanejamentoHolder> {

    private List<Planejamento> mListaPlanejamentos;
    private Context mContext;

    public PlanejamentoAdapter(List<Planejamento> mListaPlanejamentos, Context mContext) {
        this.mListaPlanejamentos = mListaPlanejamentos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PlanejamentoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_planejamento, viewGroup, false);

        return new PlanejamentoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanejamentoHolder planejamentoHolder, int i) {
        Planejamento planejamento = mListaPlanejamentos.get(i);
        planejamentoHolder.vincula(planejamento);
    }

    @Override
    public int getItemCount() {
        return mListaPlanejamentos.size();
    }

    public class PlanejamentoHolder extends RecyclerView.ViewHolder{

        private TextView txtValor, txtNome;

        public PlanejamentoHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.item_plan_nome);
            txtValor = itemView.findViewById(R.id.item_plan_valor);
        }

        public void vincula(Planejamento planejamento){
            txtNome.setText(planejamento.getmNome());
            txtValor.setText(planejamento.getmValor());
        }
    }
}
