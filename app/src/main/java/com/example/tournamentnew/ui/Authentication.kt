package com.example.tournamentnew.ui

import com.example.tournamentnew.ui.home.AuthState
import com.example.tournamentnew.ui.home.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



fun loginPlayer(email: String, password: String,onComplete:(User,String)->Unit) {
    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore


    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Get the signed-in user's UID
                val user = auth.currentUser
                val userId = user?.uid

                if (userId != null) {
                    // Access Firestore to get the user data with the UID
                    firestore.collection("users").document(userId)
                        .get()
                        .addOnSuccessListener { document ->
                            if (document != null && document.exists()) {
                                // Here, the document contains the user data from Firestore
                                val userData = document.toObject(User::class.java)?:User()
                                println("User data retrieved successfully: $userData")
                                onComplete(userData,userId)
                            } else {
                                println("No user data found!")
                            }
                        }
                        .addOnFailureListener { e ->
                            println("Error fetching user data: ${e.message}")
                        }
                } else {
                    println("User ID is null. Unable to fetch data.")
                }
            } else {
                // Handle sign-in failure
                println("Login failed: ${task.exception?.message}")
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
    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore
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