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
package com.po4yka.androidacademy

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.po4yka.androidacademy.adapter.MovieAdapter
import com.po4yka.androidacademy.adapter.OnMovieClickListener
import com.po4yka.androidacademy.model.ChangeFragment
import com.po4yka.androidacademy.model.Movie

class FragmentMoviesList : Fragment() {

    private var recycler: RecyclerView? = null
    private var listenerFragment: ChangeFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerFragment = context as? ChangeFragment
    }

    override fun onDetach() {
        super.onDetach()
        listenerFragment = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set recycler grid
        recycler = view.findViewById(R.id.filmCastRecyclerView)
        recycler?.adapter = MovieAdapter(movieListener)
        recycler?.layoutManager = GridLayoutManager(context, getSpanCount())
        recycler?.hasFixedSize()

        setMovieData()
    }

    /**
     * Count movies in line depend on screen orientation
     */
    private fun getSpanCount() =
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 3
            else -> 2
        }

    private fun setMovieData() {
        (recycler?.adapter as? MovieAdapter)?.apply {
            bindMovie(moviesList)
        }
    }

    private val movieListener = object : OnMovieClickListener {
        override fun onClick(movie: Movie) {
            listenerFragment?.toMovieDetail()
        }
    }

    companion object {
        val moviesList = listOf(
            Movie(
                title = "Avengers: End Game", rating = 4.0, posterImage = R.drawable.img_avengers,
                genres = listOf("Action", "Adventure", "Drama"), reviews = 125,
                duration = 137, ageRating = "13+", like = false
            ),
            Movie(
                title = "Tenet", rating = 5.0, posterImage = R.drawable.img_tenet,
                genres = listOf("Action", "Sci-Fi", "Thriller"), reviews = 98,
                duration = 97, ageRating = "16+", like = true
            ),
            Movie(
                title = "Black Widow", rating = 4.0, posterImage = R.drawable.img_black_widow,
                genres = listOf("Action", "Adventure", "Sci-Fi"), reviews = 38,
                duration = 102, ageRating = "13+", like = false
            ),
            Movie(
                title = "Wonder Woman 1984", rating = 5.0, posterImage = R.drawable.img_wonder_woman_1984,
                genres = listOf("Action", "Adventure", "Fantasy"), reviews = 74,
                duration = 120, ageRating = "13+", like = false
            )

        )
    }
}
