package dim.aviv.moviesapp.persistence;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import dim.aviv.moviesapp.async.DeleteAsyncTask;
import dim.aviv.moviesapp.async.InsertAsyncTask;
import dim.aviv.moviesapp.async.UpdateAsyncTask;
import dim.aviv.moviesapp.models.TheMovie;

/**
 * Created by אביב on 30/04/2019.
 */

public class TheMovieRepository {

    private TheMovieDatabase mMovieDataBase;

    public TheMovieRepository(Context context) {
        mMovieDataBase = TheMovieDatabase.getInstance(context);
    }

    public void insertMovieTask(TheMovie... movies){
        new InsertAsyncTask(mMovieDataBase.getTheMovieDao()).execute(movies);


    }

    public void updateMovie(TheMovie movie){
        new UpdateAsyncTask(mMovieDataBase.getTheMovieDao()).execute(movie);
    }
    public LiveData<List<TheMovie>> retrieveMovieTask(){
        return mMovieDataBase.getTheMovieDao().getMoviess();
    }
    public void deleteMovie(TheMovie movie){

        new DeleteAsyncTask(mMovieDataBase.getTheMovieDao()).execute(movie);
    }
}

