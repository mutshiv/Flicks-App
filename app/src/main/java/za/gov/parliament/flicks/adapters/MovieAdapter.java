package za.gov.parliament.flicks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import za.gov.parliament.flicks.R;
import za.gov.parliament.flicks.models.Movie;

/**
 * Created by vmutshinya on 4/25/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>
{
    private List<Movie> movies;
    private Context mContext;


    public MovieAdapter(Context mContext)
    {
        this.mContext = mContext;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent, false);

        return new MovieViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.title);
        holder.tvReleaseYear.setText(movie.releaseYear);
        holder.tvRating.setText("Rating " + movie.rating);
        holder.tvGenre.setText(movie.genre.toString());

        Picasso.with(mContext).load(movie.image.url).placeholder(R.drawable.dawn_of_apes).into(holder.ivPoster);
    }

    public void addMovies(List<Movie> movie)
    {
        this.movies = movie;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(movies == null)
        {
            return 0;
        }
        else
        {
            return movies.size();
        }
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvTitle;
        public TextView tvGenre;
        public TextView tvRating;
        public TextView tvReleaseYear;
        public ImageView ivPoster;

        public MovieViewHolder(View rowView)
        {
            super(rowView);

            tvTitle = (TextView)rowView.findViewById(R.id.tv_title);
            tvGenre = (TextView)rowView.findViewById(R.id.tv_genre);
            tvRating = (TextView)rowView.findViewById(R.id.tv_rating);
            tvReleaseYear = (TextView)rowView.findViewById(R.id.tv_release_year);
            ivPoster = (ImageView)rowView.findViewById(R.id.iv_poster);

        }
    }
}
