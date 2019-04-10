package br.com.juliorgm.gestmoney;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FragmentLogin extends Fragment {

    private TextView login_fragment_txt_nome;
    private EditText login_fragment_edit_email;
    private EditText login_fragment_edit_senha;
    private Button login_fragment_btn_entrar;
    private TextView login_fragment_txt_registrar;
    private TextView login_fragment_txt_recuperar;
    View view;


    public FragmentLogin() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        login_fragment_edit_email = view.findViewById(R.id.login_fragment_edit_email);
//        login_fragment_edit_senha = view.findViewById(R.id.login_fragment_edit_senha);
//        login_fragment_btn_entrar = view.findViewById(R.id.login_fragment_btn_entrar);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_login, container, false);

        login_fragment_txt_registrar = view.findViewById(R.id.login_fragment_txt_registrar);
        login_fragment_txt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragment, new FragmentRegistrar()).commit();
            }
        });
        login_fragment_txt_recuperar = view.findViewById(R.id.login_fragment_txt_recuperar);
        login_fragment_txt_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragment, new FragmentRecuperar()).commit();
            }
        });

        return view;
    }



    public interface OnFragmentInterectionListener {
    }

}








