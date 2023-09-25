package com.jmarser.alumnospracticas_1.di.appModule;


import android.content.Context;
import android.content.SharedPreferences;

import com.jmarser.alumnospracticas_1.util.Constantes;

import javax.inject.Singleton;

import at.favre.lib.armadillo.Armadillo;
import at.favre.lib.armadillo.PBKDF2KeyStretcher;
import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    private Context context;

    public SharedPreferencesModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(){

        SharedPreferences preferences = Armadillo.create(context, Constantes.PREFNAME)
                .encryptionFingerprint(context)
                .keyStretchingFunction(new PBKDF2KeyStretcher())
                .build();

        return preferences;
    }
}
