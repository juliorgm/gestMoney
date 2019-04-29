package br.com.juliorgm.gestmoney.activityLogin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import br.com.juliorgm.gestmoney.R;
public class ActivityRegistrar extends AppCompatActivity {

    private Button registrar_activity_btn_salvar;
    private EditText registrar_activity_edit_nome;
    private EditText registrar_activity_edit_email;
    private EditText registrar_activity_edit_senha;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        registrar_activity_edit_nome = findViewById(R.id.registrar_activity_edit_nome);
        registrar_activity_btn_salvar = findViewById(R.id.registrar_activity_btn_salvar);
        registrar_activity_edit_email = findViewById(R.id.registrar_activity_edit_email);
        registrar_activity_edit_senha = findViewById(R.id.registrar_activity_edit_senha);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Recuperar Senha");

        registrar_activity_btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = registrar_activity_edit_nome.getText().toString();
                String email = registrar_activity_edit_email.getText().toString();
                final String password = registrar_activity_edit_senha.getText().toString();

                if (TextUtils.isEmpty(nome)) {
                    Toast.makeText(getApplicationContext(), "Insira um nome!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Insira um email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Insira uma senha!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(ActivityRegistrar.this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (!task.isSuccessful())
                        {
                            String erro = task.getException().getMessage();
                            Toast.makeText(ActivityRegistrar.this, erro, Toast.LENGTH_LONG).show();
                        }

                        else {
                            registrarUsuario();
                            startActivity(new Intent(ActivityRegistrar.this, LoginActivity.class));
                            finish();

                        }
                    }
                });
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void registrarUsuario(){
        String email = registrar_activity_edit_email.getText().toString().trim();
        String password = registrar_activity_edit_senha.getText().toString().trim();
        progressDialog.setMessage("Realizando registro");

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alerta("Email Registrado!");
                }else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException)
                        alerta("Este usuario ja existe");
                    else{
                        alerta("Email não pode ser cadastrado!");
                }
                }
            }
        });
    }


    private void alerta(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    private void updateUI(FirebaseUser currentUser) {
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, LoginActivity.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }


}
