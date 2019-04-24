package br.com.juliorgm.gestmoney.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.models.Gastos;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.GastoHolder> {

    private List<Gastos> mListaGastos;
    private Context mContext;


    public GastoAdapter(List<Gastos> mListaGastos, Context mContext) {
        this.mListaGastos = mListaGastos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public GastoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_gasto, viewGroup, false);

        return new GastoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoHolder gastoHolder, int i) {
        Gastos gastos = mListaGastos.get(i);
//        gastoHolder.vincula(gastos);
    }

    @Override
    public int getItemCount() {
        return mListaGastos.size();
    }

    public class GastoHolder extends RecyclerView.ViewHolder {

        private RecyclerView recycler_view_gasto;
        private TextView txtDataFormularioGasto;
        private TextView txtDescricaoFormularioGasto;
        private TextView txtValorFormularioGasto;

        public GastoHolder(@NonNull View itemView) {
            super(itemView);
//            txtDataFormularioGasto = itemView.findViewById(R.id.txtDataFormularioGasto);
//            txtDescricaoFormularioGasto = itemView.findViewById(R.id.txtDescricaoFormularioGasto);
//            txtValorFormularioGasto = itemView.findViewById(R.id.txtValorFormularioGasto);
        }

        public void vincula(Gastos gastos) {
            txtDataFormularioGasto.setText(gastos.getmData());
            txtDescricaoFormularioGasto.setText(gastos.getmDescricao());
            txtValorFormularioGasto.setText((int) gastos.getmValor());
            txtValorFormularioGasto.setText(gastos.pegaValorTexto());

        }
    }

}
