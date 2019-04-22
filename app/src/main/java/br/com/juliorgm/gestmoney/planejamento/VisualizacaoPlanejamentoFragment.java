package br.com.juliorgm.gestmoney.planejamento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.adapters.PlanejamentoAdapter;
import br.com.juliorgm.gestmoney.models.Planejamento;

public class VisualizacaoPlanejamentoFragment extends Fragment {
    public static final String TAG = "Visualizar Planejamento";
    private RecyclerView recycler;
    private List<Planejamento> mListaPlanejamentos;

    public VisualizacaoPlanejamentoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizacao_planejamento, container, false);

        mListaPlanejamentos = new ArrayList<>();

        carregaViews(view);

        return view;

    }

    private void carregaViews(View view) {
        recycler = view.findViewById(R.id.plan_recycler);

        carregaLista();
    }

    private void carregaRecycler() {
        PlanejamentoAdapter adapter = new PlanejamentoAdapter(mListaPlanejamentos,getContext());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
    }

    private void carregaLista() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("usuario");

        // Read from the database
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot snapshot = dataSnapshot.child("CLZzksTIksNOsWAmDLkeFOlirVu2").child("planejamentos");
                mListaPlanejamentos.clear();
                for (DataSnapshot d: snapshot.getChildren()) {
                    Planejamento planejamento = d.getValue(Planejamento.class);
                    mListaPlanejamentos.add(planejamento);
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
}