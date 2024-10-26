package com.example.tournamentnew.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tournamentnew.data.viewModel.OrganiserDashBoardViewModel
import com.example.tournamentnew.data.viewModel.PlayerDashBoardViewModel

@Composable
fun OrganiserDashBoard(navController: NavController, organiserDashBoardViewModel: OrganiserDashBoardViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally// Spacing between rows
    ) {
        Text(text = "WELCOME ${organiserDashBoardViewModel.userData.username.uppercase()}",
            style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(16.dp) // Spacing between columns
        ) {
            OrganiserRoundedBox("Create\nTournaments") {
                navController.navigate("newtournament")
            }
            OrganiserRoundedBox("My Tournaments")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(16.dp) // Spacing between columns
        ) {
            OrganiserRoundedBox("Box 3")
            OrganiserRoundedBox("Box 4")
        }
    }
}
@Composable
fun OrganiserRoundedBox(label: String, modifier: Modifier = Modifier, navigate : ()->Unit = {}) {

    Box(
        modifier = modifier
            .size(180.dp) // Adjust size of each box
            .background(Color(0xFF56B2B2), shape = RoundedCornerShape(16.dp))
            .clickable { navigate() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontSize = 20.sp
            )
        )
    }

}


