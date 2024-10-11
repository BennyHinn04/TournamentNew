package com.example.tournamentnew.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tournamentnew.ui.theme.TournamentNewTheme

@Composable
fun HomeScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable {},
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF56B2B2)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

        ) {
            Text(
                text = "REGISTER",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.displayLarge
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable {},
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF56B2B2)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

        ) {
            Text(
                text = "ORGANISE",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.displayLarge
            )
        }
    }


}


@Preview(showBackground = true,
    widthDp = 360,
    heightDp = 800)
@Composable
fun HomeScreenPreview() {
    TournamentNewTheme {  
        HomeScreen()
    }

}