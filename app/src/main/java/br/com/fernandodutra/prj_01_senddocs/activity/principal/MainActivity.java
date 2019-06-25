package br.com.fernandodutra.prj_01_senddocs.activity.principal;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;


import java.sql.SQLException;


import br.com.fernandodutra.prj_01_senddocs.R;
import br.com.fernandodutra.prj_01_senddocs.dao.cliente.ClienteDAOLiter;

import br.com.fernandodutra.prj_01_senddocs.utils.HMAux;

public class MainActivity extends BaseActivity {

    protected DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        setupNavDrawer();
    }


}
