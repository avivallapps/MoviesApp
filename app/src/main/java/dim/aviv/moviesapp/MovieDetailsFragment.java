package dim.aviv.moviesapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import dim.aviv.moviesapp.models.TheMovie;

/**
 * Created by אביב on 04/05/2019.
 */

public class MovieDetailsFragment extends Fragment {




    private static final String TAG = "MovieDetails";

    private TextView mMovieTitle,mMovieReleaseYear,mMovieRating,mMovieGenre;
    private ImageView mMovieImage;

    private TheMovie mTheMovie = new TheMovie();
    private TheMovie mFinalMovie;

    private ImageButton mBackArrow;

    private ProgressBar mProgressbar;

    private Context mContext;




    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moviedetails, container, false);




        mContext = getActivity();
        mBackArrow = view.findViewById(R.id.toolbar_back_arrow);

       // mBackArrow.setOnClickListener(this);


        mMovieTitle = (TextView) view.findViewById(R.id.movie_title);
        mMovieReleaseYear = (TextView) view.findViewById(R.id.movie_realeaseYear);
        mMovieRating = (TextView) view.findViewById(R.id.movie_rating);
        mMovieGenre = (TextView) view.findViewById(R.id.movie_genre);
        mMovieImage = (ImageView) view.findViewById(R.id.movie_image);

        mProgressbar = (ProgressBar) view.findViewById(R.id.ProgsressDialog);



        try{
            mTheMovie = getTheMovieFromBundle();
            mTheMovie.toString();
            Log.d(TAG, "onCreateView: movietostring: " + mTheMovie.toString());
            initImageLoader();
            init();
        }catch (NullPointerException e){
            Log.e(TAG, "onCreateView: themovie was null from bundle" + e.getMessage() );
        }




   //     Log.d(TAG, "onCreateView: blablablablabla");





        return view;
    }

    private void init(){

        Log.d(TAG, "init: ::::::");
        mFinalMovie = new TheMovie();
       // mTheMovie.toString();


        mFinalMovie.setTitle(mTheMovie.getTitle());
        mFinalMovie.setImage(mTheMovie.getImage());
        mFinalMovie.setReleaseYear(mTheMovie.getReleaseYear());
        mFinalMovie.setRating(mTheMovie.getRating());
        mFinalMovie.setGenre(mTheMovie.getGenre());
        mFinalMovie.setId(mTheMovie.getId());



        mFinalMovie.toString();

        mMovieTitle.setText(mFinalMovie.getTitle());
        mMovieReleaseYear.setText("ReleaseYear: " + String.valueOf(mFinalMovie.getReleaseYear()));
        mMovieRating.setText("Rating: " + String.valueOf(mFinalMovie.getRating()));
        // mMovieGenre.setText(String.(mFinalMovie.getGenre());
//        ImageLoader imageLoader = ImageLoader.getInstance();


//        imageLoader.displayImage(mFinalMovie.getImage(),mMovieImage);
        UniversalImageLoader.setImage(mFinalMovie.getImage(),mMovieImage,mProgressbar,"");



        // mMovieImage.setText();

        Log.d(TAG, "onCreate: " + mFinalMovie.getGenre().toString());
        Log.d(TAG, "onCreate: " + mFinalMovie.getGenre());



        StringBuilder blabla = new StringBuilder();
        for(int i=0;i<mFinalMovie.getGenre().size();i++){
//            blabla.append( mFinalMovie.getGenre().get(i) + "\n");
            if(i!= mFinalMovie.getGenre().size()-1){
                blabla.append( mFinalMovie.getGenre().get(i) + ", ");
            }else{
                blabla.append( mFinalMovie.getGenre().get(i));

            }
        }
        mMovieGenre.setText("Genre: " + blabla);

    }
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    private TheMovie getTheMovieFromBundle(){
        Log.d(TAG, "getTheMovieFromBundle: argument " + getArguments());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            return bundle.getParcelable("selected_movie");
        }else{
            return null;
        }
    }


}
