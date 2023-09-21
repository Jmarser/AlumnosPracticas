package com.jmarser.alumnospracticas_1.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.databinding.ActivityMainBinding;
import com.jmarser.alumnospracticas_1.di.appComponent.AppComponent;
import com.jmarser.alumnospracticas_1.di.appComponent.DaggerAppComponent;
import com.jmarser.alumnospracticas_1.di.appModule.AppModule;
import com.jmarser.alumnospracticas_1.util.Constantes;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initInjection();

        Intent intent = getIntent();
        String email = intent.getStringExtra(Constantes.INTENT_EMAIL);
        String password = intent.getStringExtra(Constantes.INTENT_PASSWORD);

        Toast.makeText(this, email, Toast.LENGTH_LONG).show();
    }

    private void initInjection(){
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, this))
                .build();

        appComponent.inject(this);
    }
}