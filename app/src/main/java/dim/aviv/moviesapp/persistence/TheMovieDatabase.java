package dim.aviv.moviesapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import dim.aviv.moviesapp.models.TheMovie;

/**
 * Created by אביב on 30/04/2019.
 */

@Database(entities = {TheMovie.class}, version =  1,exportSchema = false)
public abstract class TheMovieDatabase extends RoomDatabase{

    public static final String DATABASE_NAME = "movieslist_db";

    private static TheMovieDatabase instance;

    static TheMovieDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TheMovieDatabase.class,
                    DATABASE_NAME
            ).build();
    }
        return instance;
    }
    public abstract TheMovieDao getTheMovieDao();

}
