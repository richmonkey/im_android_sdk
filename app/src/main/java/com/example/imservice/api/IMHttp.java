package com.example.imservice.api;

import com.example.imservice.Token;
import com.example.imservice.api.body.PostAuthRefreshToken;
import com.example.imservice.api.body.PostAuthToken;
import com.example.imservice.api.body.PostPhone;
import com.example.imservice.api.body.PostTextValue;
import com.example.imservice.api.types.Audio;
import com.example.imservice.api.types.Code;
import com.example.imservice.api.types.Image;
import com.example.imservice.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import rx.Observable;

/**
 * Created by tsung on 10/10/14.
 */
public interface IMHttp {
    @POST("/verify_code")
    Observable<Code> postVerifyCode(@Query("zone") String zone, @Query("number") String number);

    @POST("/auth/token")
    Observable<Token> postAuthToken(@Body PostAuthToken code);

    @POST("/auth/refresh_token")
    Observable<Token> postAuthRefreshToken(@Body PostAuthRefreshToken refreshToken);

    @Multipart
    @POST("/images")
    Observable<Image> postImages(@Part("file") TypedFile file);

    @Multipart
    @POST("/audios")
    Observable<Audio> postAudios(@Part("file") TypedFile file);

    @Multipart
    @PUT("/users/me/avatar")
    Observable<Image> putUsersMeAvatar(@Part("file") TypedFile file);

    @PUT("/users/me/nickname")
    Observable<Object> putUsersMeNickname(@Body PostTextValue nickname);

    @PUT("/users/me/state")
    Observable<Object> putUsersMeState(@Body PostTextValue state);

    @GET("/users")
    Observable<ArrayList<User>> getUsers(@Body List<PostPhone> phones);
}
