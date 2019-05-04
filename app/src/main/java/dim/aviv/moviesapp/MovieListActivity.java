package dim.aviv.moviesapp;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dim.aviv.moviesapp.adapters.MoviesRecyclerAdapter;
import dim.aviv.moviesapp.models.TheMovie;
import dim.aviv.moviesapp.persistence.TheMovieRepository;

/**
 * Created by אביב on 30/04/2019.
 */

public class MovieListActivity extends AppCompatActivity implements
        MoviesRecyclerAdapter.OnMovieListener{

    private static final String TAG = "MovieListActivity";

    private RecyclerView mRecyclerView;
    private Toolbar mToolBar;
    private ImageButton mAddButton;

    private Intent intent;

    private ArrayList<TheMovie> mTheMovies = new ArrayList<>();
    private MoviesRecyclerAdapter mMoviesReyclerAdapter;
    private TheMovieRepository mTheMovieRepository;
    private String whattt = "";
    private List<String> moviesnames = new ArrayList<String>();
    private TheMovie themovie = new TheMovie();


    private TheMovie tempmovie;
    private String tempstring;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);

        mRecyclerView = findViewById(R.id.recyclerView);
        mToolBar = findViewById(R.id.notes_toolbar);
        mAddButton = findViewById(R.id.add_button);




//        imgUrl = intent.getStringExtra(getString(R.string.selected_image));

//        mBackArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MovieListActivity.this,TheScanner.class);
//                startActivity(intent);
//            }
//        });



        //   findViewById(R.id.fab).setOnClickListener(this);

        mTheMovieRepository = new TheMovieRepository(this);


        //      ArrayList<TheMovie> myList =  getIntent().getParcelableExtra("arraylist");

 //       Log.d(TAG, "onCreate: my:" + myList.toString());

        initRecyclerView();
        retrieveNotes();

         tempstring = "";
        for(int i=0;i<mTheMovies.size();i++){
            tempstring = mTheMovies.get(i).getTitle();
            moviesnames.add(tempstring);
        }
//        insertFakeNotes();
//           insertFakeNotes();
//

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieListActivity.this,SimpleScannerActivity.class);
                intent.putStringArrayListExtra("nameslist",(ArrayList<String>)moviesnames);
                startActivity(intent);
            }
        });

        // setSupportActionBar((Toolbar)findViewById(R.id.notes_toolbar));
    }

    private void retrieveNotes(){
        mTheMovieRepository.retrieveMovieTask().observe(this, new Observer<List<TheMovie>>() {
            @Override
            public void onChanged(@Nullable List<TheMovie> movies) {

                if(mTheMovies.size() > 0){
                    mTheMovies.clear();
                }
                if(movies != null){
                    mTheMovies.addAll(movies);
                }
                mMoviesReyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void insertFakeNotes(){
        for(int i=0; i< 1000; i++){
            TheMovie movie = new TheMovie();
            movie.setTitle("title # " + i);
            // note.setContent("content #: " + i);
         //   movie.setTimestamp("Jan 2019");
            mTheMovies.add(movie);
        }
        mMoviesReyclerAdapter.notifyDataSetChanged();
        setTitle("Searches");
    }



    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mMoviesReyclerAdapter = new MoviesRecyclerAdapter(mTheMovies,this);
        mRecyclerView.setAdapter(mMoviesReyclerAdapter);
    }



    @Override
    public void onMovieClick(int position) {

        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("selected_movie",mTheMovies.get(position));
        startActivity(intent);
    }

//    @Override
//    public void onClick(View view) {
//
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
//    }

    private void deleteMovie(TheMovie movie){
        mTheMovies.remove(movie);
        mMoviesReyclerAdapter.notifyDataSetChanged();

        mTheMovieRepository.deleteMovie(movie);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


            deleteMovie(mTheMovies.get(viewHolder.getAdapterPosition()));
        }
    };

    private void CheckIncomingIntent(){

        try {
             intent = getIntent();
             if(intent.hasExtra("result")) {
                 whattt = intent.getStringExtra("result");

                 Log.d(TAG, "onCreate: what" + whattt);

                 try {

                     JSONObject obj = new JSONObject(whattt);

                     Log.d("My App", obj.toString());

                     Gson gson = new Gson();
                      themovie = gson.fromJson(obj.toString(), TheMovie.class);

                     mTheMovieRepository.retrieveMovieTask().observe(this, new Observer<List<TheMovie>>() {
                         @Override
                         public void onChanged(@Nullable List<TheMovie> movies) {

                             if(mTheMovies.size() > 0){
                                 mTheMovies.clear();
                             }
                             if(movies != null){
                                 mTheMovies.addAll(movies);
                                 if(!checkifexist(themovie)){
                                     mTheMovieRepository.insertMovieTask(themovie);
                                 }
                             }
                         }
                     });



                     Log.d(TAG, "onCreate: themoviee" + themovie.toString());
                 } catch (Throwable t) {
                     Log.e("My App", "Could not parse malformed JSON: \"" + whattt + "\"");
                 }

                 //    tempmovie = new TheMovie(whattt);

                 //       Log.d(TAG, "onCreate: wow!!" + tempmovie.toString());
                 //     Log.d(TAG, "onCreate: wow!!" + tempmovie);

                 getIntent().removeExtra("result");
             }
                 else{

                 Log.d(TAG, "CheckIncomingIntent: no incoming intent");
                }

        }catch (RuntimeException e){
            Log.e(TAG, "onCreate: RuntimeException" + e.getMessage() );
        }
    }

    private boolean checkifexist(TheMovie themoviecheck){
        for(int i=0;i<mTheMovies.size();i++) {
            Log.d(TAG, "checkifexist: themoviechecktitle : " + themoviecheck.getTitle());
            Log.d(TAG, "checkifexist: mthemoviestitle : " + mTheMovies.get(i).getTitle());
            if (themoviecheck.getTitle().equals(mTheMovies.get(i).getTitle())) {
                Log.d(TAG, "checkifexist: true");
                displayPopup();
                return true;

            }
        }
        Log.d(TAG, "checkifexist: false");
        return false;
    }



    @Override
    protected void onStart() {
        super.onStart();
        CheckIncomingIntent();
    }

    private void displayPopup(){


         Snackbar.make(findViewById(R.id.thelayout),"Current movie already exist in the Database.",Snackbar.LENGTH_LONG).show();
    }
}

