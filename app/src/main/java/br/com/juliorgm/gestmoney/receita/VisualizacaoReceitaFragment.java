package br.com.juliorgm.gestmoney.receita;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.juliorgm.gestmoney.adapter.ReceitaAdapter;
import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.helper.Constantes;
import br.com.juliorgm.gestmoney.model.Receita;

public class VisualizacaoReceitaFragment extends Fragment {
    private RecyclerView recyclerViewReceita;
    private List<Receita> mListaDeReceita;


    public VisualizacaoReceitaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_visualizacao_receita, container, false);
        recyclerViewReceita = view.findViewById(R.id.recycler_receita);
        mListaDeReceita = new ArrayList<>();

        carregaFirebase();
        return view;
    }

    private void carregaFirebase(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String noReceitaUsuario = user.getUid()+"/"+ Constantes.NO_FIREDATABASE_RECEITA;

        DatabaseReference reference = database.getReference(noReceitaUsuario);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mListaDeReceita.clear();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Receita receita = d.getValue(Receita.class);
                    receita.setmId(d.getKey());
                    mListaDeReceita.add(receita);
                }
                if (mListaDeReceita.size()>0)carregaRecycler();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Main", "Failed to read value.", error.toException());
            }
        });
    }

    private void carregaRecycler() {
        ReceitaAdapter adapter = new ReceitaAdapter(getContext(), mListaDeReceita);
        recyclerViewReceita.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewReceita.setAdapter(adapter);
    }

}