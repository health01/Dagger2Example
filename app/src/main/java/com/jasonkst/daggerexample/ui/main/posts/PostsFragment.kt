package com.jasonkst.daggerexample.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jasonkst.daggerexample.R
import com.jasonkst.daggerexample.ui.main.Resource
import com.jasonkst.daggerexample.viewModels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject

class PostsFragment : DaggerFragment() {
    private val TAG = "PostsFragment"

    lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var postAdapter: PostRecyclerAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, providerFactory).get(PostsViewModel::class.java)

        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().apply {
            removeObservers(viewLifecycleOwner)
            observe(viewLifecycleOwner, {
                when (it) {
                    is Resource.Loading -> Log.d(
                        TAG,
                        "onChanged: PostsFragment: LOADING..."
                    )

                    is Resource.Success -> {
                        Log.d(TAG, "onChanged: PostsFragment: got posts.")
                        it.data?.let { it1 -> postAdapter.setPosts(it1) }
                    }

                    is Resource.Error -> {
                        Log.d(
                            TAG,
                            "onChanged: PostsFragment: ERROR... " + it.message
                        )
                    }
                }
                Log.d(TAG, "onChanged: " + it.data)
            })
        }
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = linearLayoutManager
            adapter = postAdapter
        }
    }
}