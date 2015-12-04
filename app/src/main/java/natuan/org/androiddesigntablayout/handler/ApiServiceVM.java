package natuan.org.androiddesigntablayout.handler;

import java.util.Map;


import natuan.org.androiddesigntablayout.event.UserProfileDataResponse;
import natuan.org.androiddesigntablayout.model.LoginData;
import natuan.org.androiddesigntablayout.model.RegisterData;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

public interface ApiServiceVM {
    @POST("/1.0/auth")
    public void login(@QueryMap Map<String, String> options,
                      Callback<LoginData> responseJson);

    @POST("/1.0/fbAuth")
    public void fbLogin(@QueryMap Map<String, String> options,
                        Callback<LoginData> responseJson);

    @POST("/user/register")
    public void register(@QueryMap Map<String, String> options,
                         Callback<RegisterData> responseJson);

    @GET("/user/otp")
    public void otp(@QueryMap Map<String, String> options);

    @GET("/1.0/user/{id}")
    public void getProfile(@QueryMap Map<String, String> options,@Path("id") int id,
                           Callback<UserProfileDataResponse> responseJson);

    @GET("/username/{username}")
    public void getProfileUsername(@QueryMap Map<String, String> options,@Path("username") String username,
                                   Callback<UserProfileDataResponse> responseJson);

}

