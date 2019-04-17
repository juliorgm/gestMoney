package br.com.juliorgm.gestmoney.planejamento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.model.Planejamento;

public class FormularioPlanejamentoFragment extends Fragment {

    private EditText editNome;
    private EditText editReserva;
    private EditText editDataInicio;
    private EditText editDataFim;
    private Button buttonSalvar;
    private Planejamento planejamento;

    public FormularioPlanejamentoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario_planejamento, container, false);

        carregaViews();
        cliqueBotoes();;

        Intent intent = getIntent();
        planejamento = (Planejamento) intent.getSerializableExtra("PLANEJAMENTO");
        if (planejamento != null){
            preencheFormulario(planejamento);
        }
    }

    private void preencheFormulario(Planejamento planejamento) {
        editNome.setText(planejamento.getmNome());
        editReserva.setText(planejamento.getmReserva());
        editDataInicio.setText(planejamento.getmDataInicio());
        editDataFim.setText(planejamento.getmDataFim());
    }

    private void cliqueBotoes() {
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("planejamentos");
                if (livro != null) {
                    reference.child(planejamento.getmId()).setValue(planejamento);
                }else{
                    reference.push().setValue(pegaPlanejamento());
                }
                finish();
            }
        });
    }

    private void carregaViews() {
        editNome = findViewById(R.id.edit_nome);
        editReserva = findViewById(R.id.edit_reserva);
        editDataInicio = findViewById(R.id.edit_dtInicio);
        editDataFim = findViewById(R.id.edit_dtFim);
        buttonSalvar = findViewById(R.id.button_salvar);
    }

    private Planejamento pegaPlanejamento() {
        String nome = editNome.getText().toString();
        String reserva = editReserva.getText().toString();
        String dataInicio = editDataInicio.getText().toString();
        String dataFim = editDataFim.getText().toString();
        return new Planejamento(nome, reserva, dataInicio, dataFim);
    }
}









