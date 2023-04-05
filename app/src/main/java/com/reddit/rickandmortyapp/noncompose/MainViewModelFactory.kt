package com.reddit.rickandmortyapp.noncompose

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.reddit.rickandmortyapp.network.RickAndMortyApi

class MainViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val network: RickAndMortyApi
) : AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return MainViewModel(network) as T
    }
}