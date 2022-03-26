package com.example.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class AddNumberViewModel : ViewModel() {
    val IntentChanenl = Channel<mainIntent> { Channel.UNLIMITED }

    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state: StateFlow<MainViewState> get() = _viewState
    private var number = 0
    init {
        processIntent()
    }
    //process
    private fun processIntent() {
        viewModelScope.launch {
            IntentChanenl.consumeAsFlow().collect {
                when (it) {
                    is mainIntent.AddNumber -> AddNumber()
                }

            }
        }
    }

    //reduce
    private fun AddNumber() {
        viewModelScope.launch {
            _viewState.value = try {
                MainViewState.Number(++number)
            } catch (e: Exception) {
                MainViewState.Error(e.message!!)
            }

        }


    }
}