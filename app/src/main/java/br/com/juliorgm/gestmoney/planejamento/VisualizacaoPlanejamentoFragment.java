package br.com.juliorgm.gestmoney.planejamento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.adapter.PlanejamentoAdapter;
import br.com.juliorgm.gestmoney.model.Planejamento;

public class VisualizacaoPlanejamentoFragment extends Fragment {

    private RecyclerView recyclerViewPlanejamentos;
    private List<Planejamento> mListaDePlanejamentos;

    public VisualizacaoPlanejamentoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_visualizacao_planejamento, container, false);
        recyclerViewPlanejamentos = view.findViewById(R.id.recycler_planejamento);
        mListaDePlanejamentos = new ArrayList<>();
        carregaFirebase();
        return view;
    }

    private void carregaFirebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("usuario");
        //DatabaseReference reference2 = database.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DataSnapshot snapshot = dataSnapshot.child("CLZzksTIksNOsWAmDLkeFOlirVu2")
                        .child("planejamentos");

                mListaDePlanejamentos.clear();
                for (DataSnapshot d: snapshot.getChildren()) {
                    if(d.getKey().equals("pessoal")) continue;
                    Planejamento planejamento = d.getValue(Planejamento.class);
                    planejamento.setmId(d.getKey());
                    mListaDePlanejamentos.add(planejamento);
                }

                if (mListaDePlanejamentos.size()>0)carregaRecycler();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Main", "Failed to read value.", error.toException());
            }
        });
    }

    private void carregaRecycler() {
        PlanejamentoAdapter adapter = new PlanejamentoAdapter(getContext(), mListaDePlanejamentos);
        recyclerViewPlanejamentos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewPlanejamentos.setAdapter(adapter);
    }
}