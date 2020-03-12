package models.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    public final retrofit2.Retrofit retrofit;


    public RetrofitConfig(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com/api/")
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }


    public UdacityService getService(){
        return retrofit.create(UdacityService.class);
    }

}


