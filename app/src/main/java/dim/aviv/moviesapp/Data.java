package dim.aviv.moviesapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by אביב on 30/04/2019.
 */

public class Data {
    @SerializedName("details")
    @Expose
    private ArrayList<Details> details;

    public ArrayList<Details> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Details> children) {
        this.details = details;
    }

}
