package br.com.juliorgm.gestmoney.planejamento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PlayGamesAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.models.Planejamento;

public class FormularioPlanejamentoFragment extends Fragment {

    public static final String USUARIO = "usuario";
    public static final String PLANEJAMENTOS = "planejamentos";
    private EditText mNomePlanjemanto;
    private EditText mValorPlanjemanto;
    private Button mBtnSalvar;

    public FormularioPlanejamentoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulario_planejamento, container, false);

        carregaViews(view);
        cliqueBotao();

        return view;
    }

    private void cliqueBotao() {
        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarFormulario();

                String nome = mNomePlanjemanto.getText().toString().trim();
                String valor = mValorPlanjemanto.getText().toString().trim();

                Planejamento planejamento = new Planejamento(nome,valor);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(USUARIO);

                myRef.child("CLZzksTIksNOsWAmDLkeFOlirVu2").
                        child(PLANEJAMENTOS).push().setValue(planejamento);
            }
        });
    }

    private void validarFormulario() {
    }

    private void carregaViews(View view) {
        mNomePlanjemanto = view.findViewById(R.id.plan_edit_nome);
        mValorPlanjemanto = view.findViewById(R.id.plan_edit_valor);
        mBtnSalvar = view.findViewById(R.id.plan_btn_salvar);
    }
}
