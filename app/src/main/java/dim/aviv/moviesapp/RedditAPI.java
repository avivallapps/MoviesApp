package dim.aviv.moviesapp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by אביב on 30/04/2019.
 */

public interface RedditAPI {
   // String BASE_URL = "https:/www.reddit.com/r/";
    String BASE_URL = "https://api.androidhive.info/json/movies";


    @Headers("Content-Type: application/json")
    @GET("{details_name}.json")
  //  @GET("{feed_name}.json")

    Call<List<Details>> getData(@Path("details_name") String details_name);


//    void getMovies(Callback<ArrayList<Details>> callback);

//    Call<List<Details>> getDetails();
  //  Call<Details> getData(@Path("details_name") String details_name);
}
