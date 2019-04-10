package br.com.juliorgm.gestmoney;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentRegistrar extends Fragment {

    private Button btnSalvar;
    private Button btnVoltar;

    View view;


    public FragmentRegistrar() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_registrar, container, false);
        btnVoltar = view.findViewById(R.id.registrar_fragment_btn_retorno);
        btnSalvar = view.findViewById(R.id.registrar_fragment_btn_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragment, new FragmentLogin()).commit();
            }
        });
        btnVoltar = view.findViewById(R.id.registrar_fragment_btn_retorno);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
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



