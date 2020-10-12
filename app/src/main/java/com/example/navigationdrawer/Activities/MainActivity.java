package com.example.navigationdrawer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.navigationdrawer.Fragments.AlertsFragment;
import com.example.navigationdrawer.Fragments.EmailFragment;
import com.example.navigationdrawer.Fragments.InfoFragment;
import com.example.navigationdrawer.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;
            /**
             *
             */
                switch (item.getItemId()) {
                    case R.id.menu_mail:
                        fragment =  new EmailFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_alert:
                        fragment =  new AlertsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment =  new InfoFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_sub1:
                        Toast.makeText(MainActivity.this, "Click on option 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_sub2:
                        Toast.makeText(MainActivity.this, "This is option 2", Toast.LENGTH_SHORT).show();
                        break;
                }

                if(fragmentTransaction){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentFrame , fragment)
                            .commit();
                    //Para que se checkee la opción activa
                    item.setChecked(true);
                    //Añadir el nombre de la sección a la barra de acción
                    getSupportActionBar().setTitle(item.getTitle());
                    drawerLayout.closeDrawers();
                }
                return false;
            }
        });
    }

    private void setToolbar(){
        setSupportActionBar((androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar));
        //Flecha atrás o icono
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Opciones para activar el nav_menu con

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //abrir el nav_menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}