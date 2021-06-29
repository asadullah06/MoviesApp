package com.app.entertainment.movies.ui.moviesListing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.entertainment.movies.R
import com.app.entertainment.movies.data.remote.UpcomingMovies
import com.app.entertainment.movies.utils.CommonMethods
import com.google.android.material.button.MaterialButton

class MoviesListingAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<MoviesListingAdapter.ViewHolder>() {
    var moviesList: ArrayList<UpcomingMovies> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.parentLayout.setOnClickListener { onClickListener.onItemClicked(position) }
        holder.bookTicketButton.setOnClickListener { onClickListener.onButtonClicked(position) }
        CommonMethods.renderImageInView(moviesList[position].poster, holder.posterImageView)
        holder.movieNameTextView.text = moviesList[position].movieName
        holder.movieReleaseDateTextview.text = moviesList[position].releaseDate

        if (moviesList[position].isAdult) {
            holder.isAdultTextview.text = context.resources.getString(R.string.adult)
        } else
            holder.isAdultTextview.text = context.resources.getString(R.string.non_adult)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val parentLayout = view.findViewById<ConstraintLayout>(R.id.parent_layout)
        val posterImageView = view.findViewById<ImageView>(R.id.poster_imageview)
        val movieNameTextView = view.findViewById<TextView>(R.id.movie_name_textview)
        val movieReleaseDateTextview = view.findViewById<TextView>(R.id.movie_release_date_textview)
        val isAdultTextview = view.findViewById<TextView>(R.id.is_adult_textview)
        val bookTicketButton = view.findViewById<MaterialButton>(R.id.book_ticket_button)
    }
}