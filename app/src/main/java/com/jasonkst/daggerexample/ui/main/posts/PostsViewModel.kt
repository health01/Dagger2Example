package com.jasonkst.daggerexample.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.jasonkst.daggerexample.SessionManager
import com.jasonkst.daggerexample.models.Post
import com.jasonkst.daggerexample.network.main.MainApi
import com.jasonkst.daggerexample.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val mainApi: MainApi,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val TAG = "PostsViewModel"
    lateinit var posts: MediatorLiveData<Resource<out List<Post>>>

    init {
        Log.d(TAG, "PostsViewModel: viewmodel is ready...")
    }

    fun observePosts(): LiveData<Resource<out List<Post>>> {
        sessionManager.getAuthUser().value?.data?.id?.let { id ->
            posts = MediatorLiveData()
            posts.value = Resource.Loading()
            Log.d(TAG, "observePosts: user id: $id")

            mainApi.getPostsFromUser(id).onErrorReturn { throwable ->
                Log.e(TAG, "apply: ", throwable)
                val post = Post()
                post.id = -1
                val posts = ArrayList<Post>()
                posts.add(post)
                posts
            }
                ?.map {
                    if (it.isNotEmpty() || it[0].id != -1) {
                        Resource.Success(data = it)
                    } else {
                        Resource.Error(data = null, message = "Something went wrong")
                    }
                }?.let {
                    LiveDataReactiveStreams.fromPublisher(
                        it
                            .subscribeOn(Schedulers.io())
                    ).let { source ->
                        posts.addSource(source) {
                            posts.apply {
                                value = it
                                removeSource(source)
                            }
                        }
                    }
                }
        }
        return posts
    }
}