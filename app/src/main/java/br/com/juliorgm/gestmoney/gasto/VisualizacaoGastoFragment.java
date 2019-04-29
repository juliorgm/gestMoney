package br.com.juliorgm.gestmoney.gasto;


import android.content.Intent;
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

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.activityLogin.LoginActivity;
import br.com.juliorgm.gestmoney.adapter.GastoAdapter;
import br.com.juliorgm.gestmoney.helper.Constantes;
import br.com.juliorgm.gestmoney.models.Gastos;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisualizacaoGastoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private String mTipoPlanejamento;
    private List<Gastos> mListaGasto;

    public VisualizacaoGastoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visualizacao_gasto, container, false);

        carregaViews(view);
        carregaFirebase();

        return view;
    }

    private void carregaFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        if (user == null) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }

        String noDePesquisa = user.getUid()+"/"+pegaPlanejamento();

        DatabaseReference myRef = database.getReference(noDePesquisa);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mListaGasto = new ArrayList<>();

                for (DataSnapshot d:dataSnapshot.getChildren()) {
                    Gastos gastos = d.getValue(Gastos.class);
                    gastos.setmIdGasto(d.getKey());

                    mListaGasto.add(gastos);
                }
                carregaRecycler();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void carregaRecycler() {
        GastoAdapter adapter = new GastoAdapter(getContext(),mListaGasto);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }

    private String pegaPlanejamento(){
        return Constantes.NO_FIREDATABASE_PLANEJAMENTO+"/"+Constantes.NO_FIREDATABASE_PESSOAL;
    }

    private void carregaViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_gastos);
    }

}
