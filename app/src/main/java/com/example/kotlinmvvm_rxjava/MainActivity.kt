package com.example.kotlinmvvm_rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvm_rxjava.data.NewsLocalDataSource
import com.example.kotlinmvvm_rxjava.data.repo.Repository
import com.example.kotlinmvvm_rxjava.data.repo.RepositoryImplementation
import com.example.kotlinmvvm_rxjava.viewmodels.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ImageViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycle.addObserver(viewModel)
    }
}