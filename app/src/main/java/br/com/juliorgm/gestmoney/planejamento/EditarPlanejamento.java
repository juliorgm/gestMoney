package br.com.juliorgm.gestmoney.planejamento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.model.Planejamento;

import static java.security.AccessController.getContext;

public class EditarPlanejamento extends AppCompatActivity {

    private static final String USUARIO = "usuario";
    private Planejamento planejamento;
    private EditText editNome;
    private EditText editReserva;
    private EditText editDataInicio;
    private EditText editDataFim;
    private Button buttonSalvar;
    private String PLANEJAMENTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_formulario_planejamento);

        cliqueBotao();
        Intent intent = getIntent();
        planejamento = (Planejamento)intent.getSerializableExtra("PLANEJAMENTO");
        if(planejamento != null){
            carregaFormulário(planejamento);
        }
    }

    private void carregaFormulário(Planejamento planejamento) {
        editNome.setText(planejamento.getmNome());
        editReserva.setText(String.valueOf(planejamento.getmReserva()));
        editDataInicio.setText(planejamento.getmDataInicio());
        editDataFim.setText(planejamento.getmDataFim());
    }

    private Planejamento pegaPlanejamento() {
        String nome = editNome.getText().toString();
        double reserva = Double.parseDouble(editReserva.getText().toString());
        String dataInicio = editDataInicio.getText().toString();
        String dataFim = editDataFim.getText().toString();
        return new Planejamento(nome, reserva, dataInicio, dataFim);
    }

    private void cliqueBotao() {
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegaPlanejamento();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(USUARIO);
                myRef.child("CLZzksTIksNOsWAmDLkeFOlirVu2").child(PLANEJAMENTO).push().setValue( pegaPlanejamento());
                finish();
            }


        });
    }


}

