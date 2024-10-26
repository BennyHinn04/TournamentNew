package com.example.tournamentnew.data.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tournamentnew.ui.registerPlayer

class SignUpViewModel:ViewModel() {
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var useremail by mutableStateOf("")
        private set
    var userLatitude by mutableStateOf(0.00)
        private set
    var userLongitude by mutableStateOf(0.00)
        private set
    var userType by mutableStateOf("")
        private set
    fun updateUsername(Username : String) {
        username = Username
    }
    fun updatePassword(Password : String) {
        password = Password
    }
    fun updateGmail(Gmail : String) {
        useremail = Gmail
    }
    fun updateUserLocation(Latitude : Double,Longitude : Double) {
        userLatitude = Latitude
        userLongitude = Longitude
    }
    fun updateUserType(UserType : String) {
        userType = UserType
    }
    fun insertFirebase() {
        registerPlayer(
            email = useremail,
            password = password,
            userName =username,
            latitude = userLatitude,
            longitude = userLongitude,
            userType = userType
            )
    }

}