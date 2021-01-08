package com.example.nutrisaapplication.ui.main.navigationDrawer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Estas en la pantalla de home Fragment"
    }
    val text: LiveData<String> = _text
}