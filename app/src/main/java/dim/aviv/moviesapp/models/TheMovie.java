package dim.aviv.moviesapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.List;

import dim.aviv.moviesapp.GenreTypeConventer;

/**
 * Created by אביב on 30/04/2019.
 */

@Entity(tableName = "movies")
public class TheMovie implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "rating")
    private Double rating;

    @ColumnInfo(name = "releaseYear")
    private int releaseYear;

    @TypeConverters(GenreTypeConventer.class)
    @ColumnInfo(name = "genre")
    private List<String> genre;


    public TheMovie(String title, String image, Double rating, int releaseYear, List<String> genre) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    @Ignore
    public TheMovie() {
    }

    protected TheMovie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
        releaseYear = in.readInt();
        genre = in.createStringArrayList();
    }

    public static final Creator<TheMovie> CREATOR = new Creator<TheMovie>() {
        @Override
        public TheMovie createFromParcel(Parcel in) {
            return new TheMovie(in);
        }

        @Override
        public TheMovie[] newArray(int size) {
            return new TheMovie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "TheMovie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(image);
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rating);
        }
        parcel.writeInt(releaseYear);
        parcel.writeStringList(genre);
    }
}