package com.example.nutrisaapplication.ui.main.navigationDrawer.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Estas en la pantalla de slideshow Fragment"
    }
    val text: LiveData<String> = _text
}