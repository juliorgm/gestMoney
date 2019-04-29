package br.com.juliorgm.gestmoney.Adapter;

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
import br.com.juliorgm.gestmoney.model.Receita;
import br.com.juliorgm.gestmoney.receita.EditarReceita;
import br.com.juliorgm.gestmoney.receita.FormularioReceitaFragment;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.ReceitaHolder> {

    private Context mContext;
    private List<Receita> mListaDeReceitas;

    public ReceitaAdapter(Context mContext, List<Receita> mListaDePlanejamentos) {
        this.mListaDeReceitas = mListaDePlanejamentos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ReceitaAdapter.ReceitaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_receita,viewGroup,false);
        return new ReceitaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaAdapter.ReceitaHolder receitaHolder, int i) {
        Receita receita = mListaDeReceitas.get(i);
        receitaHolder.vincula(receita);
        receitaHolder.edit(receita);
        receitaHolder.delete(receita.getmId());
    }

    @Override
    public int getItemCount() {
        return mListaDeReceitas.size();
    }

    public class ReceitaHolder extends RecyclerView.ViewHolder {

        private TextView textDescricao;
        private TextView textValor;
        private TextView textData;
        private FloatingActionButton fabEditar;
        private FloatingActionButton fabDeletar;
        public ReceitaHolder(@NonNull View itemView) {
            super(itemView);

            textDescricao = itemView.findViewById(R.id.receita_fragment_txt_descricao);
            textValor = itemView.findViewById(R.id.receita_fragment_txt_valor);
            textData = itemView.findViewById(R.id.receita_fragment_txt_data);
            fabEditar = itemView.findViewById(R.id.receita_fragment_btn_editar);
            fabDeletar = itemView.findViewById(R.id.receita_fragment_btn_excluir);
        }

        public void vincula(Receita receita){
            textDescricao.setText(receita.getMdescricao());
            textValor.setText(String.valueOf(receita.getMvalor()));
            textData.setText(receita.getMdata());
        }

        public void delete(final String key){
            fabDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("usuarios");
                    reference.child(key).removeValue();
                    notifyDataSetChanged();
                }

                private void notifyDataSetChanged() {
                }
            });
        }
        public void edit(final Receita receita){
            fabEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, EditarReceita.class);
                    intent.putExtra("RECEITA",receita);
                    mContext.startActivity(intent);
                }
            });
        }
    }


}

