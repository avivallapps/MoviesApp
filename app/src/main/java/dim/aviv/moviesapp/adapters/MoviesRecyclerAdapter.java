package dim.aviv.moviesapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dim.aviv.moviesapp.R;
import dim.aviv.moviesapp.models.TheMovie;
;
/**
 * Created by אביב on 01/05/2019.
 */

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder> {
    private static final String TAG = "MoviesRecyclerAdapter";

    private ArrayList<TheMovie> mTheMovies = new ArrayList<>();
    private OnMovieListener mOnMovieListener;

    public MoviesRecyclerAdapter(ArrayList<TheMovie> mTheMovies, OnMovieListener mOnMovieListener) {
        this.mTheMovies = mTheMovies;
        this.mOnMovieListener = mOnMovieListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_list_item,parent,false);
        return  new ViewHolder(view,mOnMovieListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // Log.d(TAG, "onBindViewHolder: checking date......");

      ///  try {
       //     String month = mTheMovies.get(position).getTimestamp().substring(0, 2);
        //    month = Utility.getMonthFromNumber(month);
         //   String year = mTheMovies.get(position).getTimestamp().substring(3);
          //  String timestamp = month + " " + year;
      //      holder.timestamp.setText(timestamp);
            holder.title.setText(mTheMovies.get(position).getTitle());
        Log.d(TAG, "onBindViewHolder: this is the title" + holder.title);
            holder.timestamp.setText(String.valueOf(mTheMovies.get(position).getReleaseYear()));
            Log.d(TAG, "onBindViewHolder: checking date now......" + holder.timestamp.toString());

     ///   }catch (NullPointerException e){
            //      Log.e(TAG, "onBindViewHolder: "  ddd );
    ///    }
    }

    @Override
    public int getItemCount() {
        return mTheMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title,timestamp;
        OnMovieListener OnMovieListener;

        public ViewHolder(View itemView, OnMovieListener OnMovieListener) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            timestamp = itemView.findViewById(R.id.movie_realeaseYear);
            this.OnMovieListener = OnMovieListener;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            OnMovieListener.onMovieClick(getAdapterPosition());
        }
    }

    public interface  OnMovieListener{
        void onMovieClick(int position);
    }

}

