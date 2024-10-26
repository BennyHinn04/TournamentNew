package com.example.tournamentnew.data.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LocationViewModel:ViewModel() {
    var latitude by mutableStateOf(0.00)
        private set
    var longitude by mutableStateOf(0.00)
        private set

    fun updateCoordinates(Latitude : Double , Longitude : Double) {
        latitude = Latitude
        longitude = Longitude
    }
}