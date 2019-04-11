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
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class FragmentRegistrar extends Fragment {

    private EditText registrar_fragment_edit_nome;
    private EditText registrar_fragment_edit_email;
    private EditText registrar_fragment_edit_senha;
    private Button registrar_fragment_btn_salvar;
    private Button registrar_fragment_btn_retorno;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    View view;

    public FragmentRegistrar() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("AUTH", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("AUTH", "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_registrar, container, false);
        registrar_fragment_edit_nome = view.findViewById(R.id.registrar_fragment_edit_nome);
        registrar_fragment_edit_email = view.findViewById(R.id.registrar_fragment_edit_email);
        registrar_fragment_edit_senha = view.findViewById(R.id.registrar_fragment_edit_senha);
        registrar_fragment_btn_salvar = view.findViewById(R.id.registrar_fragment_btn_salvar);
        registrar_fragment_btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
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
    private void registrarUsuario(){
      //  String nome = registrar_fragment_edit_nome.getText().toString().trim();
        String email = registrar_fragment_edit_email.getText().toString().trim();
        String password = registrar_fragment_edit_senha.getText().toString().trim();

        progressDialog.setMessage("Realizando registro");


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            alerta("Email registrado");
                        }else{
                            alerta("Email não pode ser cadastrado");
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void alerta(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }


    public interface OnFragmentInterectionListener {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}



