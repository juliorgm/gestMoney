package br.com.juliorgm.gestmoney.planejamento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.juliorgm.gestmoney.R;

public class VisualizacaoPlanejamentoFragment extends Fragment {
    public VisualizacaoPlanejamentoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visualizacao_planejamento, container, false);
    }
}