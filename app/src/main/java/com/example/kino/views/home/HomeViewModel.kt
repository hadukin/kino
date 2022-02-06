package com.example.kino.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    private val _counter = MutableLiveData<Int>().apply {
        value = 0
    }
    val counter: LiveData<Int> = _counter

    fun increment() {
        _counter.value = _counter.value?.plus(1)
    }

}