package br.com.juliorgm.gestmoney.gasto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.models.Gastos;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioGastoFragment extends Fragment {

    public static final String PESSOAL = "pessoal";
    public static final String GASTOS = "gastos";
    private EditText editDataFormularioGasto;
    private EditText editDescricaoFormularioGasto;
    private EditText editValorFormularioGasto;
    private Button btnSalvarFormularioGasto;


    public FormularioGastoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_formulario_gasto, container, false);

        carregaViews(view);
        cliqueBotao();

        return view;
    }

    private void cliqueBotao() {
        btnSalvarFormularioGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarFormulario();
                Double valor = Double.valueOf(editValorFormularioGasto.getText().toString());
                String data = editDataFormularioGasto.getText().toString().trim();
                String descricao = editDescricaoFormularioGasto.getText().toString().trim();


                Gastos gastos = new Gastos(valor, data, descricao);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(PESSOAL);

                myRef.child("gastos").
                        child(GASTOS).push().setValue(gastos);
            }
        });
    }

    private void validarFormulario() {
    }

    private void carregaViews(View view) {
        editDataFormularioGasto = view.findViewById(R.id.editDataFormularioGasto);
        editDescricaoFormularioGasto = view.findViewById(R.id.editDescricaoFormularioGasto);
        editValorFormularioGasto = view.findViewById(R.id.editValorFomularioGasto);
        btnSalvarFormularioGasto = view.findViewById(R.id.btnSalvarFormularioGasto);
    }
}