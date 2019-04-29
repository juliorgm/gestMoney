package br.com.juliorgm.gestmoney.planejamento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.model.Planejamento;

public class FormularioPlanejamentoFragment extends Fragment {

    private EditText editNome;
    private EditText editReserva;
    private EditText editDataInicio;
    private EditText editDataFim;
    private Button buttonSalvar;
    public static final String USUARIO = "usuario";
    public static final String PLANEJAMENTOS = "planejamentos";
    private static final String TAG = "novaPostagem";
    private static final String REQUIRED = "Required";

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


    private void preencheFormulario(Planejamento planejamento) {
        editNome.setText(planejamento.getmNome());
        editReserva.setText(String.valueOf(planejamento.getmReserva()));
        editDataInicio.setText(planejamento.getmDataInicio());
        editDataFim.setText(planejamento.getmDataFim());
    }

    private void cliqueBotao() {
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegaPlanejamento();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(USUARIO);
                myRef.child("CLZzksTIksNOsWAmDLkeFOlirVu2").child(PLANEJAMENTOS).push().setValue( pegaPlanejamento());
                Toast.makeText(getContext(),"Inserção bem sucedida",Toast.LENGTH_SHORT).show();
                limparTela();
            }
        });
    }

    private void limparTela() {
    }

    private void carregaViews(View view) {
        editNome = view.findViewById(R.id.edit_nome);
        editReserva = view.findViewById(R.id.edit_valor_reserva);
        editDataInicio = view.findViewById(R.id.edit_dtInicio);
        editDataFim = view.findViewById(R.id.edit_dtFim);
        buttonSalvar = view.findViewById(R.id.button_salvar);
    }

    private Planejamento pegaPlanejamento() {
        String nome = editNome.getText().toString();
        double reserva = Double.parseDouble(editReserva.getText().toString());
        String dataInicio = editDataInicio.getText().toString();
        String dataFim = editDataFim.getText().toString();
        return new Planejamento(nome, reserva, dataInicio, dataFim);
    }
}









