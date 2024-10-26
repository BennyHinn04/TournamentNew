package com.example.tournamentnew.ui.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Email
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
import com.example.tournamentnew.data.viewModel.LocationViewModel
import com.example.tournamentnew.data.viewModel.SignUpViewModel
import com.example.tournamentnew.ui.theme.TournamentNewTheme



data class User(
    val username : String="",
    val latitude : Double=0.00,
    val longitude : Double=0.00,
    val userType : String=""
)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel= viewModel(),locationViewModel: LocationViewModel, userType: String, modifier: Modifier = Modifier) {
    var clicked by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }

    // Store constant values in remember to avoid unnecessary recompositions
    val screenTitle = remember { "${userType.uppercase()} SIGN UP" }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = screenTitle,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.padding(vertical = 7.dp))

        InputFieldWithIcon(
            icon = Icons.Filled.Email,
            label = "Gmail",
            value = viewModel.useremail,
            onValueChange = { viewModel.updateGmail(it) }
        )

        InputFieldWithIcon(
            icon = Icons.Default.Person,
            label = "Username",
            value = viewModel.username,
            onValueChange = { viewModel.updateUsername(it) }
        )

        InputFieldWithIcon(
            icon = Icons.Default.Lock,
            label = "Password",
            value = viewModel.password,
            onValueChange = { viewModel.updatePassword(it) }
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

        Spacer(modifier = Modifier.padding(8.dp))

        // Simplified UI for Sign Up button
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(Color(0xFF56B2B2), shape = RoundedCornerShape(6.dp))
                .clickable { viewModel.updateUserLocation(locationViewModel.latitude,locationViewModel.longitude)
                    viewModel.updateUserType(userType)
                    if(!viewModel.useremail.isBlank()&&!viewModel.username.isBlank()&&!viewModel.password.isBlank()) {
                        viewModel.insertFirebase()
                    }
                    navController.navigate("userlogin/$userType")
                }
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "SIGN UP",
                style = MaterialTheme.typography.displaySmall
            )
        }

        if (clicked) {
            LocationPermissionRequest(
                onLocationGranted = {
                    navController.navigate("fetchlocation") // Navigate when permission is granted
                },
                onLocationDenied = {
                    clicked = false
                    showAlert = true
                }
            )
        }

        if (showAlert) {
            Text(text = "You must allow location to continue")
        }
    }
}

@Composable
fun LocationPermissionRequest(
    onLocationGranted: () -> Unit,
    onLocationDenied: () -> Unit
) {
    val context = LocalContext.current
    val permissionGranted = remember { mutableStateOf(false) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onLocationGranted()
        } else {
            onLocationDenied()
        }
        permissionGranted.value = isGranted
    }

    LaunchedEffect(Unit) {
        val permissionStatus = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            onLocationGranted()
            permissionGranted.value = true
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Display current permission status (optional for debugging)
    if (!permissionGranted.value) {
        Text(text = "Requesting location permission...")
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

    }
}
