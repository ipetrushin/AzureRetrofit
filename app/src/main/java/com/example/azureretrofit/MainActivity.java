package com.example.azureretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Коллекция с данными о дикторах
    ArrayList<Dictor> dictors;

    // Экземпляр библиотеки и интерфейса можно использовать для всех обращений к сервису
    // формируем экземпляр библиотеки
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create()) // ответ от сервера в виде строки
            // TODO: добавить конвертор для JSON http://developer.alexanderklimov.ru/android/library/retrofit.php
            .baseUrl(AzureAPI.API_URL) // адрес API сервера
            .build();

    AzureAPI api = retrofit.create(AzureAPI.class); // описываем, какие функции реализованы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTokenClick(View v){
        Call<String> call = api.getToken(); // создаём объект-вызов
        call.enqueue(new TokenCallback()); // отправляем запрос на сервер (ставим в очередь)
    }

    public void onDictorsClick(View v) {
        // TODO: при нажатии на кнопку загружается список дикторов и выводятся в лог сведения о них
        //
        // Call<ArrayList<Dictor>> call = api.имя_метода(<URL>, <TOKEN>); // создаём объект-вызов
        // call.enqueue(new ВашКлассCallback()); // отправляем запрос на сервер (ставим в очередь)
    }

    // TODO: реализовать аналогичный класс для получения списка дикторов, при этом
    // укажите правильный тип: Callback<ArrayList<Dictor>>
    class TokenCallback implements Callback<String> {

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            if (response.isSuccessful()) {
                // TODO: response.body() содержит строку-токен, сохраните его для дальнейшего использования
                Log.d("mytag", "response: " + response.body());
            } else
                Log.d("mytag", "error " + response.code());
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            // TODO: выводить Toast в случае ошибки
            Log.d("mytag", "error " + t.getLocalizedMessage());

        }
    }
}
