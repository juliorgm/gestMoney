package br.com.juliorgm.gestmoney.activityLogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.juliorgm.gestmoney.MainActivity;
import br.com.juliorgm.gestmoney.R;

public class LoginActivity extends AppCompatActivity {

    private EditText login_activity_edit_email;
    private EditText login_activity_edit_senha;
    private Button login_activity_btn_entrar;
    private TextView login_activity_txt_registrar;
    private TextView login_activity_txt_recuperar;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        login_activity_edit_email=findViewById(R.id.login_activity_edit_email);
        login_activity_edit_senha=findViewById(R.id.login_activity_edit_senha);
        login_activity_btn_entrar=findViewById(R.id.login_activity_btn_entrar);
        login_activity_txt_registrar=findViewById(R.id.login_activity_txt_registrar);
        login_activity_txt_recuperar=findViewById(R.id.login_activity_txt_recuperar);
        login_activity_btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = login_activity_edit_email.getText().toString();
                final String password = login_activity_edit_senha.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Insira um email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Insira uma senha!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Falha ao logar,email ou senha invalidos!\nTente novamente.", Toast.LENGTH_LONG).show();
                        }

                        else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });

        login_activity_txt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ActivityRegistrar.class);
                startActivity(intent);
            }
        });
        login_activity_txt_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ActivityRecuperar.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void acessarUsuario(){
        String email = login_activity_edit_email.getText().toString().trim();
        String password = login_activity_edit_senha.getText().toString().trim();
        progressDialog.setMessage("Realizando registro");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            alerta("ACESSANDO!");
                        }else{
                            alerta("FALHOU!");
                        }
                        progressDialog.dismiss();

                        // ...
                    }
                });
    }


    private void alerta(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}


