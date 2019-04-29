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
import br.com.juliorgm.gestmoney.receita.FormularioReceitaFragment;
import br.com.juliorgm.gestmoney.receita.VisualizacaoReceitaFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabReceitaFragment extends Fragment {


    public TabReceitaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_receita, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabReceita);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerReceita);
        viewPager.setAdapter(new TabReceitaFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        getActivity().setTitle("Receitas");
        return view;
    }

    public interface OnFragmentInteractionListener {
    }

    private class TabReceitaFragmentPageAdapter extends FragmentPagerAdapter {

        public TabReceitaFragmentPageAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new FormularioReceitaFragment();
                default:
                    return new VisualizacaoReceitaFragment();
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
