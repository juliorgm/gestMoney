package br.com.juliorgm.gestmoney.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.model.Planejamento;
import br.com.juliorgm.gestmoney.planejamento.FormularioPlanejamentoFragment;

import static android.content.ContentValues.TAG;

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
//                    FragmentManager fragmentManager = mContext.getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.add(R.id.planejamento.VisualizacaoPlanejamentoFragment, planejamento.FormularioPlanejamentoFragment);
//                    fragmentTransaction.commit();


                }
            });
        }
    }


}

