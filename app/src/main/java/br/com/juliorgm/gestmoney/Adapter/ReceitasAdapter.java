package br.com.juliorgm.gestmoney.Adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

interface ReceitasAdapter {
    @NonNull
    ReceitaAdapter.ReceitaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

    void onBindViewHolder(@NonNull ReceitaAdapter.ReceitaHolder receitaHolder, int i);

    int getItemCount();
}
