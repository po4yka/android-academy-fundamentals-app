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
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.po4yka.androidacademy.adapter.ActorAdapter
import com.po4yka.androidacademy.model.Actor
import com.po4yka.androidacademy.model.ChangeFragment

class FragmentMoviesDetails : Fragment() {

    private var listener: ChangeFragment? = null
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.filmCastRecyclerView)
        recycler?.adapter = ActorAdapter()
        recycler?.hasFixedSize()

        view.findViewById<Button>(R.id.backBtn).setOnClickListener {
            listener?.toMoviesList()
        }

        setActorsData()
    }

    private fun setActorsData() {
        (recycler?.adapter as? ActorAdapter)?.bindActor(actorsList)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ChangeFragment
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"

        @JvmStatic
        fun newInstance(movieId: String) =
            FragmentMoviesDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_MOVIE_ID, movieId)
                }
            }

        val actorsList = listOf(
            Actor("Robert", "Downey Jr.", R.drawable.cast1),
            Actor("Chris", "Evans", R.drawable.cast2),
            Actor("Mark", "Ruffalo", R.drawable.cast3),
            Actor("Chris", "Hemsworth", R.drawable.cast4)
        )
    }
}
