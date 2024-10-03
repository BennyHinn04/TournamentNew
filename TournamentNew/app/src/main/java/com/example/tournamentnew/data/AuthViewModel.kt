package com.example.tournamentnew.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tournamentnew.ui.home.AuthState


class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {


    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> get() = _authState

    private val _currentUserId = MutableLiveData<String?>()
    val currentUserId: LiveData<String?> get() = _currentUserId

    fun loginUser(email: String, password: String) {
        _authState.value = AuthState.Loading
        authRepository.loginPlayer(email, password) { result: AuthState, userId: String? ->
            _authState.value = result
            if (result is AuthState.Success) {
                _currentUserId.value = userId // Store the user's UID
            }
        }
    }


    fun registerUser(email: String, password: String, displayName: String) {
        _authState.value = AuthState.Loading
        authRepository.registerPlayer(email, password, displayName) { result ,userId->
            _authState.value = result
            if (result is AuthState.Success) {
                _currentUserId.value = userId // Store the user's UID
            }
        }
    }
}



