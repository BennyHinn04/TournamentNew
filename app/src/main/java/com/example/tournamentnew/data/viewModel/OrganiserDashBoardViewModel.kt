package com.example.tournamentnew.data.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tournamentnew.ui.home.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class OrganiserDashBoardViewModel : ViewModel(){
    var userData = User()
    var userId by mutableStateOf("")
    var tournamentName by mutableStateOf("")
        private set
    var sportsType by mutableStateOf("")
        private set
    var registrationFee by mutableStateOf(0)
        private set
    var tournamentLatitude by mutableStateOf(0.00)
        private set
    var tournamentLongitude by mutableStateOf(0.00)
        private set
    fun updateTournamentName(TournamentName: String) {
        tournamentName = TournamentName
    }
    fun updateSportsType(SportsType : String) {
        sportsType = SportsType
    }
    fun updateRegistrationFee(RegistrationFee: Int) {
        registrationFee = RegistrationFee
    }
    fun updateTournamentCoordinates(latitude: Double,longitude: Double) {
        tournamentLatitude = latitude
        tournamentLongitude = longitude
    }
    fun updateUser(user : User) {
        userData = user
    }
    fun updateUserId(userID : String) {
        userId = userID
    }
    fun addTouranament(newtournament : Tournament) {
        val firestore = Firebase.firestore
        firestore.collection("tournaments")
            .add(newtournament)
            .addOnSuccessListener {
                println("User data stored successfully!")
            }
            .addOnFailureListener { e ->
                println("Error storing user data: ${e.message}")
            }
    }
}
data class Tournament(
    var tournamentName : String = "",
    var sportsType : String = "",
    var registrationFee : Int = 0,
    var organiserId : String = "",
    var teams : List<String> = listOf(),
    var matches : List<String> = listOf(),
    var latitude : Double = 0.00,
    var longitude : Double = 0.00
)