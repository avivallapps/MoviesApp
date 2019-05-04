package dim.aviv.moviesapp.async;

import android.os.AsyncTask;

import dim.aviv.moviesapp.models.TheMovie;
import dim.aviv.moviesapp.persistence.TheMovieDao;

/**
 * Created by אביב on 30/04/2019.
 */

public class UpdateAsyncTask extends AsyncTask<TheMovie,Void,Void> {
    private TheMovieDao mTheMovieDao;

    public UpdateAsyncTask(TheMovieDao mTheMovieDao) {
        this.mTheMovieDao = mTheMovieDao;
    }

    @Override
    protected Void doInBackground(TheMovie... movies) {
        mTheMovieDao.update(movies);
        return null;
    }
}
