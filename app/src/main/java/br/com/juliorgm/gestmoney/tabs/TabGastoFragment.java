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
import br.com.juliorgm.gestmoney.gasto.FormularioGastoFragment;
import br.com.juliorgm.gestmoney.gasto.VisualizacaoGastoFragment;

public class TabGastoFragment extends Fragment {


    public TabGastoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_gasto, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabGasto);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerGasto);
        viewPager.setAdapter(new TabGastoFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        getActivity().setTitle("Gastos");
        return view;
    }

    private class TabGastoFragmentPageAdapter extends FragmentPagerAdapter {

        public TabGastoFragmentPageAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new FormularioGastoFragment();
                default:
                    return new VisualizacaoGastoFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Novo";
                default:
                    return "Visualizar";
            }
        }
    }

}
