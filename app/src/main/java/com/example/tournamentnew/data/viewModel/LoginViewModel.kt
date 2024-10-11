package com.example.tournamentnew.data.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel:ViewModel() {
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var latitude by mutableStateOf(0.00)
        private set
    var longitude by mutableStateOf(0.00)
        private set
    fun updateUsername(Username : String) {
        username = Username
    }
    fun updatePassword(Password : String) {
        password = Password
    }
    fun updateCoordinates(Latitude : Double , Longitude : Double) {
        latitude = Latitude
        longitude = Longitude
    }
}