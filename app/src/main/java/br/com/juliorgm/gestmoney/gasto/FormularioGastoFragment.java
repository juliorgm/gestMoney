package br.com.juliorgm.gestmoney.gasto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.juliorgm.gestmoney.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioGastoFragment extends Fragment {


    public FormularioGastoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario_gasto, container, false);
    }

}
