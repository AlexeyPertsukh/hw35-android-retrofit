package network;

import java.util.List;

import jsonplaceholder_class.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/users")
    Call<List<User>> getAllUsers();
}
