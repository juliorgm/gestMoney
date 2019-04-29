package br.com.juliorgm.gestmoney.receita;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.model.Receita;

public class EditarReceita extends AppCompatActivity {

    private static final String USUARIO = "usuario";
    public static final String RECEITA = "receita";
    private Receita receita;
    private EditText editDescricao;
    private EditText editValor;
    private EditText editData;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_formulario_planejamento);

        cliqueBotao();
        Intent intent = getIntent();
        receita = (Receita)intent.getSerializableExtra("RECEITA");
        if(receita != null){
            carregaFormulário(receita);
        }
    }

    private void carregaFormulário(Receita receita) {
        editDescricao.setText(receita.getmDescricao());
        editValor.setText(String.valueOf(receita.getmValor()));
        editData.setText(receita.getmData());

    }

    private Receita pegaReceita() {
        String descricao = editDescricao.getText().toString();
        double valor = Double.parseDouble(editValor.getText().toString());
        String data = editData.getText().toString();

        return new Receita(descricao,valor,data);
    }

    private void cliqueBotao() {
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pegaReceita();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(USUARIO);
                myRef.child("usuario").child(RECEITA).push().setValue( pegaReceita());
                finish();
            }


        });
    }


}
