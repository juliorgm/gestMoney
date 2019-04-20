package br.com.juliorgm.gestmoney.activity_login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juliorgm.gestmoney.MainActivity;
import br.com.juliorgm.gestmoney.R;

public class ActivityRegistrar extends AppCompatActivity {

    private final String sCAMPO_VAZIO = "Campo Vazio";
    private final String sIMAGEM_PERFIL_DEFAULT = "https://firebasestorage.googleapis.com/v0/b/gestmoney-af405.appspot.com/o/imagem_padrao%2Fuser_default.png?alt=media&token=c236a5ed-22e1-4f65-86b9-8f0dc3d35d1a";
    private boolean mFormularioValido;
    private Button mBtnSalvar;
    private EditText mEditNome;
    private EditText mEditEmail;
    private EditText mEditSenha;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        mBtnSalvar = findViewById(R.id.registrar_activity_btn_salvar);
        mEditEmail = findViewById(R.id.registrar_activity_edit_email);
        mEditSenha = findViewById(R.id.registrar_activity_edit_senha);
        mEditNome = findViewById(R.id.registrar_activity_edit_nome);
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);

        validarCampoEventoFoco();

        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCampos();
                if (mFormularioValido) registrarUsuario();
            }
        });

    }

    private void validarCampoEventoFoco() {
        mEditEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validaCampoEmailVazio(mEditEmail);
                }
            }
        });
        mEditNome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validaCampoVazio(mEditNome);
                }
            }
        });
        mEditSenha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validaCampoSenha(mEditSenha);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void registrarUsuario() {
        String email = mEditEmail.getText().toString().trim();
        String password = mEditSenha.getText().toString().trim();
        mProgressDialog.setMessage("Realizando registro");
        mProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    alerta("Email Registrado!");
                    insereNovoUsuario();
                } else {
                    Exception e = task.getException();
                    Log.e("Registro", e.getMessage());
                    alerta("Email não pode ser cadastrado!");
                }
                mProgressDialog.dismiss();
            }
        });
    }

    private void insereNovoUsuario() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            inserirUsuarioNoBanco(user);
            atualizaPerfil(user);
            vaiParaTelaPrincipal();
        }
    }

    private void vaiParaTelaPrincipal() {
        Intent intent = new Intent(ActivityRegistrar.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void validaCampos() {
        validaCampoVazio(mEditNome);
        validaCampoEmailVazio(mEditEmail);
        validaCampoSenha(mEditSenha);
    }

    private void validaCampoSenha(EditText mEditSenha) {
        validaCampoVazio(mEditSenha);
        if (!mFormularioValido) return;

        String senha = mEditSenha.getText().toString().trim();
        if (senha.length() < 6) {
            mEditSenha.setError("A senha deve pelo menos 6 caracteres");
            mFormularioValido = false;
        } else {
            mFormularioValido = true;
        }
    }

    private void validaCampoEmailVazio(EditText mEditEmail) {
        validaCampoVazio(mEditEmail);
        if (!mFormularioValido) return;

        String email = mEditEmail.getText().toString().trim();


        if (email.matches(".+@.+\\..+")) {
            mFormularioValido = true;
        } else {
            mEditEmail.setError("Email inválido");
            mFormularioValido = false;
        }
    }

    private void validaCampoVazio(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            campo.setError(sCAMPO_VAZIO);
            mFormularioValido = false;
        } else {
            mFormularioValido = true;
        }
    }

    private void atualizaPerfil(FirebaseUser user) {
        String nomeUser = mEditNome.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nomeUser)
                .setPhotoUri(Uri.parse(sIMAGEM_PERFIL_DEFAULT))
                .build();

        user.updateProfile(profileUpdates);
    }

    private void inserirUsuarioNoBanco(FirebaseUser user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuario");
        myRef.child(user.getUid()).child("planejamentos").child("pessoal").child("gastos").setValue("");
    }

    private void alerta(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
