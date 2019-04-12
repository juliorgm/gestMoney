package br.com.juliorgm.gestmoney.tabs;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.juliorgm.gestmoney.R;
import br.com.juliorgm.gestmoney.planejamento.FormularioPlanejamentoFragment;
import br.com.juliorgm.gestmoney.planejamento.VisualizacaoPlanejamentoFragment;
import br.com.juliorgm.gestmoney.receita.FormularioReceitaFragment;
import br.com.juliorgm.gestmoney.receita.VisualizacaoReceitaFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabPlanejamentoFragment extends Fragment {


    public TabPlanejamentoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_planejamento, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabPlanejamento);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerPlanejamento);
        viewPager.setAdapter(new TabPlanejamentoFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    private class TabPlanejamentoFragmentPageAdapter extends FragmentPagerAdapter {

        public TabPlanejamentoFragmentPageAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new FormularioPlanejamentoFragment();
                default:
                    return new VisualizacaoPlanejamentoFragment();
            }
        }
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Novo";
                default:
                    return "Visualizar";
            }
        }
    }
}