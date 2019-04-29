package br.com.juliorgm.gestmoney.receita;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.helper.Constantes;
import br.com.juliorgm.gestmoney.model.Receita;

public class FormularioReceitaFragment extends Fragment {

    private EditText mDescricao;
    private EditText mValor;
    private EditText mData;
    private Button mBtnSalvar;
    public static final String USUARIO = "usuario";

    public FormularioReceitaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario_receita, container, false);

        carregaViews(view);
        cliqueBotao();

        return view;
    }

    private void preencheFormulario(Receita receita) {
        mDescricao.setText(receita.getmDescricao());
        mValor.setText(String.valueOf(receita.getmValor()));
        mData.setText(receita.getmData());

    }

    private void cliqueBotao() {
        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(user.getUid());
                myRef.child(Constantes.NO_FIREDATABASE_RECEITA).push().setValue(pegaReceita());
                Toast.makeText(getContext(),"Inserção bem sucedida",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregaViews(View view){
        mDescricao = (EditText)view.findViewById(R.id.receita_fragment_edit_descricao);
        mValor = (EditText) view.findViewById(R.id.receita_fragment_edit_valor);
        mData = (EditText)view.findViewById(R.id.receita_fragment_edit_data);
        mBtnSalvar = (Button)view.findViewById(R.id.receita_fragment_btn_salvar);
    }

    private Receita pegaReceita() {
        String descricao = mDescricao.getText().toString();
        double valor = Double.parseDouble(mValor.getText().toString());
        String data = mData.getText().toString();
        return new Receita(descricao, valor, data);
    }
}
