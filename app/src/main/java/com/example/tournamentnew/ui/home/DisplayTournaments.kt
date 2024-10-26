package com.example.tournamentnew.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tournamentnew.data.viewModel.PlayerDashBoardViewModel
import com.example.tournamentnew.data.viewModel.Tournament

@Composable
fun DisplayTournamentScreen(navController: NavController,playerDashBoardViewModel: PlayerDashBoardViewModel) {
    val tournaments by playerDashBoardViewModel.tournaments

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = ArrangementzX.spacedBy(8.dp)
    ) {
        items(tournaments) { tournament ->
            TournamentItem(tournament)
        }
    }
}

@Composable
fun TournamentItem(tournament: Tournament) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF56B2B2), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text("Tournament Name: ${tournament.tournamentName}", color = Color.White)
            Text("Sports Type: ${tournament.sportsType}", color = Color.White)
            Text("Registration Fee: ${tournament.registrationFee}", color = Color.White)
        }
    }
}
