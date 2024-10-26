package com.example.tournamentnew.data.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tournamentnew.ui.home.User
import com.example.tournamentnew.ui.loginPlayer

class LoginViewModel:ViewModel() {
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    fun updateUsername(Username : String) {
        username = Username
    }
    fun updatePassword(Password : String) {
        password = Password
    }
    fun updateEmail(Email : String) {
        email = Email
    }
    fun authenticateUser(onComplete : (User, String)->Unit) {

        loginPlayer(
            email = email,
            password = password,
            onComplete
        )

    }
}