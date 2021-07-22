package com.example.kotlinmvvm_rxjava.viewmodels

import android.util.Log
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.kotlinmvvm_rxjava.data.entities.SourceDto
import com.example.kotlinmvvm_rxjava.data.network.NewsAPI
import com.example.kotlinmvvm_rxjava.data.repo.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

private const val TAG = "ImageViewModel"


class ImageViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel(), LifecycleObserver {

    var items = ObservableField<SourceDto>()


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun refresh() {
        repository.getNewsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(TAG, "refresh: " + it.size)
                }, {
                    Log.e(TAG, "refresh: ", it)
                }
            )
    }
}