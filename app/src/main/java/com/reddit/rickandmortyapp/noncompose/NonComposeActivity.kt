package com.reddit.rickandmortyapp.noncompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.reddit.rickandmortyapp.R
import com.reddit.rickandmortyapp.network.RickAndMortyService

class NonComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_non_compose)

        val rickMortyApi = RickAndMortyService.api
        val factory = MainViewModelFactory(this, rickMortyApi)
        val vmProvider = ViewModelProvider(this, factory)
        val viewModel = vmProvider[MainViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.list)
        val adapter = MainRvAdapter()
        recyclerView.adapter = adapter

        viewModel.output.observe(this) {
            when (it) {
                is MainViewModel.ViewState.Error -> {
                    // TODO show error
                }
                is MainViewModel.ViewState.Success -> adapter.update(it.data)
            }
        }
    }
}