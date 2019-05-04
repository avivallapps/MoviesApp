package dim.aviv.moviesapp.async;

import android.os.AsyncTask;

import dim.aviv.moviesapp.models.TheMovie;
import dim.aviv.moviesapp.persistence.TheMovieDao;

/**
 * Created by אביב on 30/04/2019.
 */

public class DeleteAsyncTask extends AsyncTask<TheMovie,Void,Void>{
    private TheMovieDao mTheMovieDao;

    public DeleteAsyncTask(TheMovieDao mTheMovieDao) {
        this.mTheMovieDao = mTheMovieDao;
    }

    @Override
    protected Void doInBackground(TheMovie... movies) {
        mTheMovieDao.delete(movies);
        return null;
    }
}


