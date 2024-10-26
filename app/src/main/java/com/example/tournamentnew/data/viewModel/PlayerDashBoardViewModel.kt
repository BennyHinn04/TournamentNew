package com.example.tournamentnew.data.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tournamentnew.ui.home.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PlayerDashBoardViewModel: ViewModel() {
    var userData = User()
    var userId by mutableStateOf("")
    var sportsType by mutableStateOf("")
    var tournamentLatitude by mutableStateOf(0.00)
    var tournamentLongitude by mutableStateOf(0.00)
    private val _tournaments = mutableStateOf<List<Tournament>>(emptyList())
    val tournaments: State<List<Tournament>> = _tournaments
    fun updateUser(user : User) {
        userData = user
    }
    fun updateUserId(userID : String) {
        userId = userID
    }
    fun updateSportsType(SportsType : String) {
        sportsType = SportsType
    }
    fun updateTournamentCoordinates(latitude : Double,longitude : Double) {
        tournamentLatitude = latitude
        tournamentLongitude = longitude
    }
    fun getTournamentDetails(sportsType : String,latitude : Double , longitude : Double) {
        fetchAndFilterTournaments(latitude,longitude,sportsType)
    }
    fun fetchAndFilterTournaments(userLat: Double, userLon: Double, userSportsType: String) {
        val firestore = Firebase.firestore
        firestore.collection("tournaments")
            .get()
            .addOnSuccessListener { querySnapshot ->
                println("Hello ")
                val filteredTournaments = querySnapshot.documents.filter { document ->
                    val tournamentLat = document.getDouble("latitude") ?: 0.0
                    val tournamentLon = document.getDouble("longitude") ?: 0.0
                    val sportsType = document.getString("sportsType") ?: ""

                    val distance = haversine(userLat, userLon, tournamentLat, tournamentLon)

                    // Filter by sports type and distance
                    sportsType == userSportsType && distance <= 20.0
                }.map { document ->
                    // Map the Firestore document to your Tournament data class
                    Tournament(
                        tournamentName = document.getString("tournamentName") ?: "Unknown",
                        sportsType = document.getString("sportsType") ?: "Unknown",
                        registrationFee = document.getLong("registrationFee")?.toInt() ?: 0
                    )
                }

                // Display the filtered tournaments
                _tournaments.value = filteredTournaments
            }
            .addOnFailureListener { exception ->
                println("Error fetching tournaments: ${exception.message}")
            }
    }

    fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371 // Radius of the Earth in kilometers
        println("tournament vertices"+lat2+" "+lon2)
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        print("value"+R*c)

        return R * c // Distance in kilometers
    }
    fun displayTournaments(tournaments: List<DocumentSnapshot>) {
        tournaments.forEach { tournament ->
            println("Tournament: ${tournament.getString("tournamentName")}")
        }
    }

}