package com.jmarser.alumnospracticas_1.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.albunes.view.AlbunesFragment;
import com.jmarser.alumnospracticas_1.databinding.ActivityMainBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.di.appModule.SharedPreferencesModule;
import com.jmarser.alumnospracticas_1.portadas.view.PortadasFragment;
import com.jmarser.alumnospracticas_1.usuarios.view.UsuariosFragment;
import com.jmarser.alumnospracticas_1.util.Constantes;
import com.jmarser.alumnospracticas_1.util.NavFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private ActivityMainBinding binding;


    @Inject
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInjection();

        getDataIntent();

        // Por defecto mostramos un fragment determinado
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(), new UsuariosFragment()).commit();
        }

        initListener();

    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();

        appComponent.inject(this);
    }

    private void getDataIntent(){
        Intent intent = getIntent();
        String email = intent.getStringExtra(Constantes.INTENT_EMAIL);
        String password = intent.getStringExtra(Constantes.INTENT_PASSWORD);

        Toast.makeText(this, email, Toast.LENGTH_LONG).show();
    }

    private void initListener(){
        binding.navigationButton.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment selectedFragment = null;

        switch (item.getItemId()){
            case R.id.menu_user:
                selectedFragment = new UsuariosFragment();
                break;
            case R.id.menu_albums:
                selectedFragment = new AlbunesFragment();
                break;
            case R.id.menu_galery:
                selectedFragment = new PortadasFragment();
                break;
        }

        NavFragment.replaceFragment(getSupportFragmentManager(), selectedFragment, selectedFragment.getClass().getName());
        return true;
    }
}