package com.jmarser.alumnospracticas_1.di.appModule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jmarser.alumnospracticas_1.api.wsApi.WsApi;
import com.jmarser.alumnospracticas_1.util.Constantes;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ConnectionModule {

    @Provides
    @Singleton
    public WsApi providesApi(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constantes.SERVER_URL)
                .client(okHttpClient)
                .build();

        return retrofit.create(WsApi.class);
    }
}
