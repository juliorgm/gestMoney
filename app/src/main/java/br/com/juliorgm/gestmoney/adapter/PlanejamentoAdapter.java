package br.com.juliorgm.gestmoney.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.model.Planejamento;

public class PlanejamentoAdapter extends RecyclerView.Adapter<PlanejamentoAdapter.PlanejamentosHolder> {

    private Context mContext;
    private List<Planejamento> mListaDePlanejamentos;

    public PlanejamentoAdapter(Context mContext, List<Planejamento> mListaDePlanejamentos) {
        this.mListaDePlanejamentos = mListaDePlanejamentos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PlanejamentoAdapter.PlanejamentosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_planejamento, viewGroup, false);
        return new PlanejamentosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanejamentoAdapter.PlanejamentosHolder planejamentosHolder, int i) {
        Planejamento planejamento = mListaDePlanejamentos.get(i);
        planejamentosHolder.vincula(planejamento);
        planejamentosHolder.editar(planejamento);
        planejamentosHolder.delete(planejamento.getmId());
        planejamentosHolder.novoGasto(planejamento.getmId());
    }

    @Override
    public int getItemCount() {
        return mListaDePlanejamentos.size();
    }

    public class PlanejamentosHolder extends RecyclerView.ViewHolder {

        private TextView textNome;
        private TextView textReserva;
        private TextView textDataInicio;
        private TextView textDataFim;
        private FloatingActionButton fabEditar;
        private FloatingActionButton fabDeletar;

        public PlanejamentosHolder(@NonNull View itemView) {
            super(itemView);

            textNome = itemView.findViewById(R.id.txt_nome);
            textReserva = itemView.findViewById(R.id.txt_valreserva);
            textDataInicio = itemView.findViewById(R.id.txt_dt_inicio);
            textDataFim = itemView.findViewById(R.id.txt_dt_fim);
            fabEditar = itemView.findViewById(R.id.fab_editar);
            fabDeletar = itemView.findViewById(R.id.fab_deletar);
        }

        public void vincula(Planejamento planejamento) {
            textNome.setText(planejamento.getmNome());
            textReserva.setText(String.valueOf(planejamento.getmReserva()));
            textDataInicio.setText(planejamento.getmDataInicio());
            textDataFim.setText(planejamento.getmDataFim());
        }

        public void delete(final String key) {
            fabDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("usuarios");
                    reference.child(key).removeValue();
                    notifyDataSetChanged();
                }
            });
        }

        public void editar(final Planejamento planejamento) {
            fabEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void novoGasto(String getmId) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


}

