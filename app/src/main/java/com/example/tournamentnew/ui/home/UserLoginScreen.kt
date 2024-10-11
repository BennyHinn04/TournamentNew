package com.example.tournamentnew.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.tournamentnew.ui.theme.TournamentNewTheme
import androidx.compose.runtime.getValue
import com.example.tournamentnew.data.viewModel.SignUpViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: SignUpViewModel, userType : String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${userType.uppercase()} LOGIN",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.padding(7.dp))
        InputFieldWithIcon(
            icon = Icons.Default.Person, // Replace with a custom drawable if needed
            label = "Username",
            value = viewModel.username,
            onValueChange = {
                viewModel.updateUsername(it)
            }
        )

        InputFieldWithIcon(
            icon = Icons.Default.Lock,
            label = "Password" ,
            value = viewModel.password,
            onValueChange = {
                viewModel.updatePassword(it)
            }

        )
        Card(
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
                .clickable {
                },
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Text(
                text = "NEW USER? SIGN UP",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodySmall
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
                text = "LOGIN",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.displaySmall
            )
        }



    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFieldWithIcon(
    icon: ImageVector,
    label: String,
    value: String,
    onValueChange : (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Outlined TextField
        OutlinedTextField(
            value = value,
            onValueChange = {onValueChange(it)},
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),

        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800,
)
@Composable
fun UserLoginScreenPreview() {
    TournamentNewTheme {
        LoginScreen(navController = rememberNavController(), viewModel = viewModel(),userType = "Organiser")
    }
}
