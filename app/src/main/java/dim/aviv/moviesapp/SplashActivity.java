package dim.aviv.moviesapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dim.aviv.moviesapp.models.TheMovie;
import dim.aviv.moviesapp.persistence.TheMovieRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    private static final String BASE_URL = "https://api.androidhive.info/json/";


    private TheMovieRepository mTheMovieRepository;


    private Button mCheckButton;

    private ProgressBar mProgressBar;

    private  final ArrayList<TheMovie> mMovies = new ArrayList<TheMovie>();
    private List<TheMovie> MoviesList;
    //private   List<Movie> mMovies = new ArrayList<Movie>();


    private TheMovie tempMovie = new TheMovie();

    private TheMovie mFinalMovie = new TheMovie();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Boolean isFirstRun = getSharedPreferences("PREFERENCE",MODE_PRIVATE)
                .getBoolean("isFirstRun",false);

        if (isFirstRun){
            startActivity(new Intent(SplashActivity.this,MovieListActivity.class));
            finish();
            Log.d(TAG, "onCreate: kaboom");
        } else{

            getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun",true).apply();


            Log.d(TAG, "onCreate: isfirstrun" + isFirstRun);


            mTheMovieRepository = new TheMovieRepository(this);

            mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);

            mProgressBar.setVisibility(View.VISIBLE);



            init();

        }




    }

//    private void saveChanges(){
//        saveNewMovie();
//    }



    private void saveNewMovie(TheMovie... mFinalMovies){
        mTheMovieRepository.insertMovieTask(mFinalMovies);
    }

    private void disabledEditMode(){


        Log.d(TAG, "disabledEditMode: whatshapp");
        MoviesList = new ArrayList<>();


        // productList2 = productList;
        MoviesList.addAll(mMovies);

        mMovies.clear();

        sortingList(MoviesList);
        mMovies.addAll(MoviesList);
        Collections.reverse(mMovies);



        saveNewMovie(mMovies.toArray(new TheMovie[mMovies.size()]));



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
                Intent intent = new Intent(SplashActivity.this,MovieListActivity.class);
                //  intent.putExtra("arraylist",mMovies);  //myArrayList is ArrayList<Integer>
                startActivity(intent);
                finish();
            }

        },4000);



    }

    private List<TheMovie> sortingList(List<TheMovie> listit){
        //    Product tempProduct = new Product();
        int i,j;
        for(i=0; i<listit.size();i++){

            for(j=0;j <listit.size()-i-1;j++){
                if(listit.get(j).getReleaseYear() > listit.get(j+1).getReleaseYear()){
                    tempMovie = listit.get(j);
                    //      listit.get(j) = listit.get(j+1);
                    //     listit.get(j +1) = tempProduct;
                    listit.set(j,listit.get(j+1));
                    listit.set(j+1,tempMovie);
                }
            }

        }
        return listit;

    }

    private void init(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RedditAPI redditAPI = retrofit.create(RedditAPI.class);
        Call<List<Details>> call = redditAPI.getData("movies");
//        Call<List<Details>> call = redditAPI.getDetails();

        call.enqueue(new Callback<List<Details>>() {
            @Override
            public void onResponse(Call<List<Details>> call, Response<List<Details>> response) {



                for(Details details: response.body()) {

                    //  Log.d(TAG, "checkthat: " + details.toString());

                    mMovies.add(new TheMovie(
                            details.getTitle(),
                            details.getImage(),
                            details.getRating(),
                            details.getReleaseYear(),
                            details.getGenre()
                    ));
                }

                for (int i=0;i<mMovies.size();i++){
                    Log.d(TAG, "printings: " + mMovies.get(i).toString());
                }

                disabledEditMode();



            }
            @Override
            public void onFailure(Call<List<Details>> call, Throwable t) {

                Log.e(TAG, "onFailure: Something went wrong: " + t.getMessage() );
                //    Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
