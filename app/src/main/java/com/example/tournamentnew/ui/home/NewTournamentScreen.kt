package com.example.tournamentnew.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tournamentnew.data.viewModel.LocationViewModel
import com.example.tournamentnew.data.viewModel.NewTournamentViewModel
import com.example.tournamentnew.data.viewModel.OrganiserDashBoardViewModel
import com.example.tournamentnew.data.viewModel.Tournament
import com.example.tournamentnew.ui.theme.TournamentNewTheme

@Composable
fun NewTournamentScreen(navController: NavController,organiserDashBoardViewModel: OrganiserDashBoardViewModel,locationViewModel: LocationViewModel) {
    var clicked by remember { mutableStateOf(false) }
    var customLocation by remember { mutableStateOf(false)}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputFieldWithIcon(
            icon = Icons.AutoMirrored.Filled.Label,
            label = "Tournament Name",
            value = organiserDashBoardViewModel.tournamentName,
            onValueChange = {
                organiserDashBoardViewModel.updateTournamentName(it)
            }

        )
        InputFieldWithIcon(
            icon = Icons.Default.EmojiEvents,
            label = "Sports Tyoe",
            value = organiserDashBoardViewModel.sportsType,
            onValueChange = {
                organiserDashBoardViewModel.updateSportsType(it)
            }

        )
        InputFieldWithIcon(
            icon = Icons.Default.AttachMoney,
            label = "",
            value = organiserDashBoardViewModel.registrationFee.toString(),
            onValueChange = {
                organiserDashBoardViewModel.updateRegistrationFee(it.toInt())
            }

        )
        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        // Simplified UI for location button using Box instead of Card
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color(0xFF676261), shape = RoundedCornerShape(6.dp))
                .clickable { clicked = true }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "FETCH LOCATION",
                style = MaterialTheme.typography.bodySmall
            )

        }
        LocationAlert(joinTournamentClicked = clicked, {
            clicked = false
        }) {
            navController.navigate("fetchlocation")
            customLocation = true
            clicked = false
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color(0xFF56B2B2), shape = RoundedCornerShape(6.dp))
                .clickable {
                    if(customLocation)
                        organiserDashBoardViewModel.updateTournamentCoordinates(locationViewModel.latitude, locationViewModel.longitude)
                    else
                        organiserDashBoardViewModel.updateTournamentCoordinates(organiserDashBoardViewModel.userData.latitude,
                            organiserDashBoardViewModel.userData.longitude)
                    val newTournament = Tournament(
                        tournamentName = organiserDashBoardViewModel.tournamentName,
                        sportsType = organiserDashBoardViewModel.sportsType,
                        organiserId = organiserDashBoardViewModel.userId,
                        registrationFee = organiserDashBoardViewModel.registrationFee,
                        latitude = organiserDashBoardViewModel.tournamentLatitude,
                        longitude = organiserDashBoardViewModel.tournamentLongitude
                    )
                    organiserDashBoardViewModel.addTouranament(newTournament)
                    navController.popBackStack()
                }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CONFIRM",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}



@Preview
@Composable
fun NewTournamentScreenPreview() {
    TournamentNewTheme {
        //NewTournamentScreen(rememberNavController())
    }
}