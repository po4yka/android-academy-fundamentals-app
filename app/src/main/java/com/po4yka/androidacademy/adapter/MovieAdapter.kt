/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.po4yka.androidacademy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.po4yka.androidacademy.R
import com.po4yka.androidacademy.model.Movie

class MovieAdapter(private var movieListener: OnMovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    private var moviesList = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
        holder.itemView.setOnClickListener { movieListener.onClick(moviesList[position]) }
    }

    override fun getItemCount(): Int = moviesList.size

    fun bindMovie(newMoviesList: List<Movie>) {
        moviesList = newMoviesList
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val poster = itemView.findViewById<ImageView>(R.id.imgTitlePoster)
    private val ageRating = itemView.findViewById<TextView>(R.id.filmAgeRating)
    private val like = itemView.findViewById<ImageView>(R.id.isFilmLiked)
    private val genres = itemView.findViewById<TextView>(R.id.filmGenres)
    private val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
    private val reviews = itemView.findViewById<TextView>(R.id.filmReviews)
    private val title = itemView.findViewById<TextView>(R.id.filmTitle)
    private val duration = itemView.findViewById<TextView>(R.id.tvDuration)

    fun bind(movie: Movie) {
        poster.setImageResource(movie.posterImage)
        ageRating.text = movie.ageRating
        like.setImageResource(if (movie.like) R.drawable.ic_like_filed else R.drawable.ic_like_empty)
        genres.text = movie.genres.joinToString(", ")
        ratingBar.rating = movie.rating.toFloat()
        reviews.text = itemView.resources.getQuantityString(R.plurals.review, movie.reviews, movie.reviews)
        title.text = movie.title
        duration.text = itemView.resources.getString(R.string.duration_unit, movie.duration)
    }
}

interface OnMovieClickListener {
    fun onClick(movie: Movie)
}
