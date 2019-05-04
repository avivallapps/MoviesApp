package dim.aviv.moviesapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import dim.aviv.moviesapp.models.TheMovie;

/**
 * Created by אביב on 30/04/2019.
 */

@Dao
public interface TheMovieDao {

    @Insert
    void insertMovies(TheMovie...movies);

    @Query("SELECT * FROM movies")
    LiveData<List<TheMovie>> getMoviess();

    @Query("SELECT * FROM movies WHERE title Like :title")
    List<TheMovie> getMoviesWithCustomQuery(String title);

    @Delete
    int delete(TheMovie... movies);

    @Update
    int update(TheMovie... movies);
}
