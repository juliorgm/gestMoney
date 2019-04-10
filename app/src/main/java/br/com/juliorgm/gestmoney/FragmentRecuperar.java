package br.com.juliorgm.gestmoney;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FragmentRecuperar extends Fragment {

    private TextView recuperar_fragment_txtTitulo_email;
    private EditText recuperar_fragment_edit_email;
    private TextView recuperar_fragment_txt_mensagem;
    private Button recuperar_fragment_btn_enviar;
    private Button recuperar_fragment_btn_retorno;
    View view;

    public FragmentRecuperar() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_recuperar, container, false);
        recuperar_fragment_btn_enviar = view.findViewById(R.id.recuperar_fragment_btn_enviar);
        recuperar_fragment_btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragment, new FragmentLogin()).commit();
            }
        });
        recuperar_fragment_btn_retorno = view.findViewById(R.id.recuperar_fragment_btn_retorno);
        recuperar_fragment_btn_retorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragment, new FragmentLogin()).commit();
            }
        });

        return view;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
