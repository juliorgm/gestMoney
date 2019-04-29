package br.com.juliorgm.gestmoney.gasto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.helper.Constantes;
import br.com.juliorgm.gestmoney.models.Gastos;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioGastoFragment extends Fragment {

    private EditText editDataFormularioGasto;
    private EditText editDescricaoFormularioGasto;
    private EditText editValorFormularioGasto;
    private Spinner spinner;
    private Button btnSalvarFormularioGasto;


    public FormularioGastoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_formulario_gasto, container, false);

        carregaViews(view);
        carregaSpinner();
        cliqueBotao();

        return view;
    }

    private void carregaSpinner() {
        if (spinner != null) {
            List<String> categorias = Arrays.asList(Constantes.LISTA_NOME_CATEGORIAS);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,categorias );
            spinner.setAdapter(adapter);
        }
    }

    private void cliqueBotao() {
        btnSalvarFormularioGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarFormulario();
                Double valor = Double.valueOf(editValorFormularioGasto.getText().toString());
                String data = editDataFormularioGasto.getText().toString().trim();
                String descricao = editDescricaoFormularioGasto.getText().toString().trim();
                String categoria = spinner.getSelectedItem().toString();
                Gastos gastos = new Gastos(valor, data, categoria,descricao);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

                Task task = myRef.child(user.getUid())
                        .child(Constantes.NO_FIREDATABASE_PLANEJAMENTO)
                        .child(Constantes.NO_FIREDATABASE_PESSOAL)
                        .push()
                        .setValue(gastos);


                if (task != null){
                    limpaTexto();
                }

            }
        });
    }

    private void limpaTexto() {
        editDataFormularioGasto.getText().clear();
        editDescricaoFormularioGasto.getText().clear();
        editValorFormularioGasto.getText().clear();
    }

    private void validarFormulario() {
    }

    private void carregaViews(View view) {
        editDataFormularioGasto = view.findViewById(R.id.editDataFormularioGasto);
        editDescricaoFormularioGasto = view.findViewById(R.id.editDescricaoFormularioGasto);
        editValorFormularioGasto = view.findViewById(R.id.editValorFomularioGasto);
        spinner = view.findViewById(R.id.spinnerCategoriaFormularioGasto);
        btnSalvarFormularioGasto = view.findViewById(R.id.btnSalvarFormularioGasto);
    }
}