package com.example.tournamentnew.data

import com.example.tournamentnew.ui.home.AuthState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun loginPlayer(email: String, password: String, callback: (AuthState, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userId = user?.uid // Get the user's UID
                    callback(AuthState.Success, userId) // Pass the userId
                } else {
                    callback(AuthState.Error(task.exception?.message ?: "Login Failed"), null) // Pass null userId on error
                }
            }
    }

    fun registerPlayer(
        email: String,
        password: String,
        displayName: String,
        callback: (AuthState, String?) -> Unit // Update the callback signature
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Set the display name
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            callback(AuthState.Success, user.uid) // Pass user ID on success
                        } else {
                            callback(AuthState.Error(updateTask.exception?.message ?: "Failed to set display name"), null)
                        }
                    }
                } else {
                    callback(AuthState.Error(task.exception?.message ?: "Registration Failed"), null) // Pass null userId on error
                }
            }
    }
}
