package br.com.juliorgm.gestmoney.planejamento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

public class PlanejamentoMain {

    private FloatingActionButton fabAddPlanejamento;
    private RecyclerView recyclerViewPlanejamentos;
    private List<Planejamento> mListaDePlanejamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddPlanejamento = findViewById(R.id.fab_AddPlan);
        mListaDePlanejamentos = new ArrayList<>();


        fabAddPlanejamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanejamentoMain.this, FormularioPlanejamentoFragment.class);
                startActivity(intent);
            }
        });

        carregaFirebase();
    }

    private void carregaFirebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("planejamentos");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mListaDePlanejamentos.clear();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Planejamento planejamento = d.getValue(Planejamento.class);
                    planejamento.setmId(d.getKey());
                    mListaDePlanejamentos.add(planejamento);
                }
                carregaRecycler();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Main", "Failed to read value.", error.toException());
            }
        });
    }

    private void carregaRecycler() {
        recyclerViewPlanejamentos = findViewById(R.id.recycler_planejamento);
        PlanejamentoAdapter adapter = new PlanejamentoAdapter(this,mListaDePlanejamentos);
        recyclerViewPlanejamentos.setAdapter(adapter);
    }
}
