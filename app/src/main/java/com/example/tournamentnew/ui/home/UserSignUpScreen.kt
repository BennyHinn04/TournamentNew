package com.example.tournamentnew.ui.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tournamentnew.data.viewModel.SignUpViewModel
import com.example.tournamentnew.ui.theme.TournamentNewTheme

@Composable
fun SignUpScreen(navController: NavController,viewModel:SignUpViewModel,userType: String,modifier: Modifier = Modifier) {
    var clicked by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${userType.uppercase()} SIGN UP",
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
            label = "Password",
            value = viewModel.password,
            onValueChange = {
                viewModel.updatePassword(it)
            }

        )
        Spacer(modifier = Modifier.padding(6.dp))
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable { clicked = true
                },
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF676261)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Text(
                text = "FETCH LOCATION",
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
                text = "SIGN UP",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.displaySmall
            )
        }
        if(clicked) {
            LocationPermissionRequest( {
                navController.navigate("")
            }) {
                showAlert = true
            }
        }
        if(showAlert) {
            Text(text="You must allow to continue")
        }

    }
}

@Composable
fun LocationPermissionRequest(
    onLocationGranted: () -> Unit,
    onLocationDenied: () -> Unit
) {
    // Permission state to keep track of whether we have permission or not
    var permissionGranted by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Permission launcher for requesting location permission
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
        if (isGranted) {
            onLocationGranted()
        } else {
            onLocationDenied()
        }
    }

    // Check if we already have location permission
    val permissionStatus = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    // If we don't have permission, request it when the composable first launches
    LaunchedEffect(Unit) {
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            print("Permission is granted")
            permissionGranted = true
            onLocationGranted()
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Content based on permission status
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (permissionGranted) {
            Text(text = "Location permission granted!")
        } else {
            Text(text = "Requesting location permission...")
        }
    }


}


@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
fun SignUpScreenPreview() {
    TournamentNewTheme {
        SignUpScreen(navController = rememberNavController(), viewModel = viewModel(), userType = "Player")
    }
}
