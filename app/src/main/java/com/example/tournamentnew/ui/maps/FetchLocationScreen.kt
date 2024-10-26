package com.example.tournamentnew.ui.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tournamentnew.data.viewModel.LocationViewModel
import com.example.tournamentnew.ui.theme.TournamentNewTheme
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun FetchUserLocationScreen(
    navController: NavController, // Screen-specific ViewModel
    locationViewModel: LocationViewModel,  // Location-specific ViewModel
    modifier: Modifier = Modifier

) {val cameraPositionState = rememberCameraPositionState()
    var markerState by remember { mutableStateOf<MarkerState?>(null) } // Holds marker state
    var showDialog by remember { mutableStateOf(false) } // State to control dialog visibility

    Column(modifier = modifier) {
        GoogleMap(
            modifier = Modifier
                //.weight(1f)
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            //properties = MapProperties(isMyLocationEnabled = true),
            onMapClick = { latLng ->
                markerState = MarkerState(position = latLng)
                showDialog = true
            }
        ) {
            // Place a marker if a location has been tapped
            markerState?.let {
                Marker(
                    state = it, // Use the MarkerState here
                    title = "Selected Location"
                )


            }
        }

    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {

            },
            title = { Text("Confirm Location") },
            text = { Text("Do you want to confirm the selected location?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Set the selected location based on marker position
                        locationViewModel.updateCoordinates(markerState?.position?.latitude?:0.00,markerState?.position?.longitude?:0.00)
                        navController.popBackStack() // Navigate back to the login screen
                        showDialog = false // Close the dialog
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false // Close the dialog without any action
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
fun UserLocationPreview() {
    TournamentNewTheme {
        FetchUserLocationScreen(navController = rememberNavController(), viewModel())
    }
}

