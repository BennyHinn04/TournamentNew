package com.example.tournamentnew.data

import com.example.tournamentnew.ui.home.AuthState
import com.example.tournamentnew.ui.home.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AuthRepository {
    /*
        private val auth = FirebaseAuth.getInstance()
        private val firestore = Firebase.firestore

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
            userName: String,
            latitude : Double,
            longitude : Double,
            userType : String,
            //callback: (AuthState, String?) -> Unit // Update the callback signature
        ) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Set the display name
                        val userId = auth.currentUser?.uid
                        if (userId != null) {
                            // Create a map to store the user data
                            val user = User(userName,latitude,longitude, userType )

                            // Store user data under the "users" collection with userId as the document ID
                            firestore.collection("users").document(userId)
                                .set(user)
                                .addOnSuccessListener {
                                    println("User data stored successfully!")
                                }
                                .addOnFailureListener { e ->
                                    println("Error storing user data: ${e.message}")
                                }
                        }
                        /*
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

                       */
                    } else {
                        //callback(AuthState.Error(task.exception?.message ?: "Registration Failed"), null) // Pass null userId on error
                        println("Sign-up failed: ${task.exception?.message}")
                    }
                }
        }
        */

}
