package com.example.nutrisaapplication.ui.main.navigationDrawer.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Estas en la pantalla de gallery Fragment"
    }
    val text: LiveData<String> = _text
}