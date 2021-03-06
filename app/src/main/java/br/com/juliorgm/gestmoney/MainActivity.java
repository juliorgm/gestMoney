package br.com.juliorgm.gestmoney;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import br.com.juliorgm.gestmoney.activityLogin.LoginActivity;
import br.com.juliorgm.gestmoney.tabs.TabGastoFragment;
import br.com.juliorgm.gestmoney.tabs.TabPlanejamentoFragment;
import br.com.juliorgm.gestmoney.tabs.TabReceitaFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_main,new TabGastoFragment());
        ft.commit();


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();

        if(usuario == null){
            vaiParaLoginActivity();
        }else{
            carregaInformacoesUsuario(usuario);
        }
    }

    private void carregaInformacoesUsuario(FirebaseUser usuario) {
        View view = navigationView.getHeaderView(0);
        ImageView imagemMenuTopo =  view.findViewById(R.id.img_menu_perfil);
        TextView txtNome = view.findViewById(R.id.txt_menu_perfil_nome);
        TextView txtEmail = view.findViewById(R.id.txt_menu_perfil_email);
        Picasso.get().load(usuario.getPhotoUrl()).into(imagemMenuTopo);
        txtNome.setText(usuario.getDisplayName());
        txtEmail.setText(usuario.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_gasto) {
            fragment = new TabGastoFragment();
        } else if (id == R.id.nav_receita) {
            fragment = new TabReceitaFragment();
        } else if (id == R.id.nav_planejamento) {
            fragment = new TabPlanejamentoFragment();
        } else if (id == R.id.nav_sair) {
            FirebaseAuth.getInstance().signOut();
            vaiParaLoginActivity();
            return true;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content_main,fragment);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void vaiParaLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        finish();
    }
}
