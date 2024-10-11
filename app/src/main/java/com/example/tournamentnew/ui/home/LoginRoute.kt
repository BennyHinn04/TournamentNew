package com.example.tournamentnew.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tournamentnew.data.viewModel.AuthViewModel


@Composable
fun LoginRoute(navController: NavHostController, viewModel: AuthViewModel = viewModel()) {
    val authState by viewModel.authState.observeAsState()

    when (authState) {
        is AuthState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate("dashboard") {
                    popUpTo("login") { inclusive = true }  // Remove the login screen from the backstack
                }
            }
        }
        is AuthState.Error -> {
            val errorMessage = (authState as AuthState.Error).message
            // Display the error message
        }
        AuthState.Loading -> {
            // Show loading spinner
        }
        AuthState.Idle -> {
            // Show the login form or initial screen
        }
        else -> {

        }

    }

    // Login form UI
}

sealed class AuthState {
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
    object Loading : AuthState()
    object Idle : AuthState()
}

