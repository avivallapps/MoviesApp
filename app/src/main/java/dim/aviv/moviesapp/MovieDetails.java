package dim.aviv.moviesapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import dim.aviv.moviesapp.models.TheMovie;
import dim.aviv.moviesapp.persistence.TheMovieDao;

/**
 * Created by אביב on 02/05/2019.
 */

public class MovieDetails extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MovieDetails";

    private TextView mMovieTitle,mMovieReleaseYear,mMovieRating,mMovieGenre;
    private ImageView mMovieImage;

    private TheMovie mTheMovie = new TheMovie();
    private TheMovie mFinalMovie;

    private ImageButton mBackArrow;

    private ProgressBar mProgressbar;

    private ViewPager mViewPager;
    private SectionsStatePagerAdapter PagerAdapter;


    private TheMovie tempThemovie;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);


        mBackArrow = (ImageButton) findViewById(R.id.toolbar_back_arrow);

        mBackArrow.setOnClickListener(this);

        tempThemovie  = getIntent().getParcelableExtra("selected_movie");


        Log.d(TAG, "onCreate: tempthemovie" + tempThemovie.toString());


        Bundle bundle = new Bundle();
        bundle.putParcelable("selected_movie", tempThemovie);
        MovieDetailsFragment fragobj = new MovieDetailsFragment();
        fragobj.setArguments(bundle);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.view_pager,fragobj);
        transaction.commit();

    //    setupFrament();

    }

    private void setupFrament(){
        PagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        PagerAdapter.addFragment(new MovieDetailsFragment(),"first fragment");

        mViewPager.setAdapter(PagerAdapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.toolbar_back_arrow: {
                finish();
                break;
            }
        }
    }

}
