package br.com.juliorgm.gestmoney;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;


public class FragmentRegistrar extends Fragment implements View.OnClickListener {

    private EditText registrar_fragment_edit_nome;
    private EditText registrar_fragment_edit_email;
    private EditText registrar_fragment_edit_senha;
    private Button registrar_fragment_btn_salvar;
    private Button registrar_fragment_btn_retorno;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    View view;

    public FragmentRegistrar() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrar_fragment_edit_nome = view.findViewById(R.id.registrar_fragment_edit_nome);
        registrar_fragment_edit_email = view.findViewById(R.id.registrar_fragment_edit_email);
        registrar_fragment_edit_senha = view.findViewById(R.id.registrar_fragment_edit_senha);
        registrar_fragment_btn_salvar = view.findViewById(R.id.registrar_fragment_btn_salvar);
        registrar_fragment_btn_salvar.setOnClickListener(this);
        progressDialog = new ProgressDialog(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void registrarUsuario(){
        String nome = registrar_fragment_edit_nome.getText().toString().trim();
        String email = registrar_fragment_edit_email.getText().toString().trim();
        String password = registrar_fragment_edit_senha.getText().toString().trim();

        if(TextUtils.isEmpty(nome)){
            Toast.makeText(getActivity(),"Precisa inserir nome",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getActivity(),"Precisa inserir email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getActivity(),"Precisa inserir uma senha",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando registro");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(),"Seu email foi registrado",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(),"Nao pode registrar esse email",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        registrarUsuario();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_registrar, container, false);
        registrar_fragment_btn_salvar = view.findViewById(R.id.registrar_fragment_btn_salvar);
        registrar_fragment_btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragment, new FragmentLogin()).commit();
            }
        });
        registrar_fragment_btn_retorno = view.findViewById(R.id.registrar_fragment_btn_retorno);
        registrar_fragment_btn_retorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragment, new FragmentLogin()).commit();
            }
        });

        return view;
    }



    public interface OnFragmentInterectionListener {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}



