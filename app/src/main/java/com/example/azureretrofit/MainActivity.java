package com.example.azureretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTokenClick(View v){
        // формируем экземпляр библиотеки
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create()) // ответ от сервера в виде строки
                .baseUrl(AzureAPI.API_URL) // адрес API сервера
                .build();

        AzureAPI api = retrofit.create(AzureAPI.class); // описываем, какие функции реализованы

        Call<String> call = api.getToken(); // создаём объект-вызов

        call.enqueue(new TokenCallback()); // отправляем запрос на сервер


    }

    class TokenCallback implements Callback<String> {

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            if (response.isSuccessful()) {
                Log.d("mytag", "response: " + response.body());
            } else
                Log.d("mytag", "error " + response.code());
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            Log.d("mytag", "error " + t.getLocalizedMessage());

        }
    }
}
