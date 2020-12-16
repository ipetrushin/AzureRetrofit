package com.example.azureretrofit;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/*
curl -v -X POST \
 "https://westus.api.cognitive.microsoft.com/sts/v1.0/issueToken" \
 -H "Content-type: application/x-www-form-urlencoded" \
 -H "Content-Length: 0" \
 -H "Ocp-Apim-Subscription-Key: <SUBSCRIPTION_KEY>"

 */
interface AzureAPI {
    String API_URL = "https://eastasia.api.cognitive.microsoft.com";
    String key = "1db01788ad86488d90d573a7fe502c11 ";
    @POST("/sts/v1.0/issueToken") // путь к API
    @Headers({
            "Content-type: application/x-www-form-urlencoded",
            "Content-Length: 0",
            "Ocp-Apim-Subscription-Key: "+key
    })
    // String - формат ответа от сервера
    Call<String> getToken();

    // Тип ответа, действие, тип запроса
}
