package com.reddit.rickandmortyapp.noncompose

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reddit.rickandmortyapp.network.RickAndMortyApi
import com.reddit.rickandmortyapp.network.RickAndMortyCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val network: RickAndMortyApi): ViewModel() {
    private val tag = "MainViewModel"

    val output = MutableLiveData<ViewState>()

    init {
        network.fetchCharactersRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                output.value = ViewState.Success(it.results)
            }, {
                Log.e(tag, "", it)
            })
    }

    sealed class ViewState {
        data class Success(val data: List<RickAndMortyCharacter>): ViewState()
        data class Error(val message: String): ViewState()
    }
}