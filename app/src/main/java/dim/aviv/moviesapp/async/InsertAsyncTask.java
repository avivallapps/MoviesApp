package dim.aviv.moviesapp.async;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import dim.aviv.moviesapp.models.TheMovie;
import dim.aviv.moviesapp.persistence.TheMovieDao;

/**
 * Created by אביב on 30/04/2019.
 */

public class InsertAsyncTask extends AsyncTask<TheMovie,Void,Void> {
    private static final String TAG = "InsertAsyncTask";

    private TheMovieDao mTheMovieDao;

    public InsertAsyncTask(TheMovieDao dao) {
        mTheMovieDao = dao;
    }

    @Override
    protected Void doInBackground(TheMovie... movies) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        mTheMovieDao.insertMovies(movies);
        return null;
    }


//    @Override
//    protected Void doInBackground(TheMovie movies) {
//        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
//        mTheMovieDao.insertMovies(movies);
//        return null;
//    }

}

