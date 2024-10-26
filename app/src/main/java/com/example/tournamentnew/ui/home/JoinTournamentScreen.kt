package com.example.tournamentnew.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tournamentnew.data.viewModel.LocationViewModel
import com.example.tournamentnew.data.viewModel.PlayerDashBoardViewModel
import com.example.tournamentnew.data.viewModel.Tournament

@Composable
fun JoinTournamentScreen(navController: NavController,playerDashBoardViewModel: PlayerDashBoardViewModel,locationViewModel: LocationViewModel) {
    var joinTournamentClicked by remember { mutableStateOf(false) }
    var customLocation by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = playerDashBoardViewModel.sportsType,
            onValueChange = {
                playerDashBoardViewModel.updateSportsType(it)
            } ,
            label = {Text("Sport")},
            modifier = Modifier.fillMaxWidth(),

            )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color(0xFF676261), shape = RoundedCornerShape(6.dp))
                .clickable { joinTournamentClicked = true }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "FETCH LOCATION",
                style = MaterialTheme.typography.bodySmall
            )

        }
        Spacer(modifier = Modifier.padding(6.dp))
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color(0xFF56B2B2), shape = RoundedCornerShape(6.dp))
                .clickable {
                    if(customLocation)
                        playerDashBoardViewModel.updateTournamentCoordinates(locationViewModel.latitude,
                            locationViewModel.longitude)
                    else
                        playerDashBoardViewModel.updateTournamentCoordinates(playerDashBoardViewModel.userData.latitude,
                            playerDashBoardViewModel.userData.longitude)
                    playerDashBoardViewModel.getTournamentDetails(playerDashBoardViewModel.sportsType
                    ,playerDashBoardViewModel.tournamentLatitude
                    ,playerDashBoardViewModel.tournamentLongitude)
                    navController.navigate("displaytournament")
                }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "SEARCH",
                style = MaterialTheme.typography.displaySmall
            )
        }


    }
    LocationAlert(joinTournamentClicked, {
        joinTournamentClicked = false

    }) {
        navController.navigate("fetchlocation")
        customLocation = true
        joinTournamentClicked = false
    }

}
@Composable
fun LocationAlert(joinTournamentClicked:Boolean,confirmButton : ()->Unit
                  ,dismissButton: () -> Unit) {
    if(joinTournamentClicked) {
        AlertDialog(
            onDismissRequest = {

            },
            title = { Text("Confirm Location") },
            text = { Text("Do you want to use your default location?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirmButton()
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dismissButton()
                    }
                ) {
                    Text("No")
                }
            }
        )
    }

}