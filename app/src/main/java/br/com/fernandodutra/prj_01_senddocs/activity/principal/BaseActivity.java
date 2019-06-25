package br.com.fernandodutra.prj_01_senddocs.activity.principal;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.fernandodutra.prj_01_senddocs.R;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 03/06/2019
 * Time: 21:39
 * Prj_01_SendDocs
 */
public class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;

    // Configura a ToolBar
    protected void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    // Configura o Nav Drawer
    protected void setupNavDrawer() {
        // Drawer Layout
        final ActionBar actionBar = getSupportActionBar();

        // Ícone do menu do Nav Drawer
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        if (navigationView != null && drawerLayout != null) {
            // atualizar a imagem e os textos do header do menu lateral
            setNavViewValues(navigationView, R.string.nav_drawer_username, R.string.nav_drawer_email, R.mipmap.ic_launcher);

            // Trata o evento de clique no menu
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    // Seleciona a linha
                    menuItem.setChecked(true);
                    // Fecha o menu
                    drawerLayout.closeDrawers();
                    // Trata o evento do menu
                    onNavDrawerItemSelected(menuItem);
                    return true;
                }
            });
        }
    }

    // Atualiza os dados do Header do Navigation View
    private void setNavViewValues(NavigationView navigationView, int username, int email, int img) {
        View headerView = navigationView.getHeaderView(0);
        TextView tvNome = headerView.findViewById(R.id.tNome);
        TextView tvEmail = headerView.findViewById(R.id.tEmail);
        ImageView imgView = headerView.findViewById(R.id.img);

        tvNome.setText(username);
        tvEmail.setText(email);
        imgView.setImageResource(img);
    }

    // Trata o evento do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_item_clientes:
                break;
            case R.id.nav_item_departamentos:
                break;
            case R.id.nav_item_tipodocs:
                break;
            case R.id.nav_item_usuarios:
                break;
            case R.id.nav_item_protocolos:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        return super.onOptionsItemSelected(item);
    }
}
