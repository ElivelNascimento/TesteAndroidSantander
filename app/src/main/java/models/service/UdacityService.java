package models.service;

import models.model.ResponseLogin;
import models.model.TransactionList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UdacityService {


    @POST("login")
    @FormUrlEncoded
    Call<ResponseLogin> login(@Field("user") String user, @Field("password") String password);

    @GET("statements/{id}")
    Call<TransactionList> listHistoric(@Path("id") int id);
}
