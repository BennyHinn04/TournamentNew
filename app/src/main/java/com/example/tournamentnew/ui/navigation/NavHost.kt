package com.example.tournamentnew.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.tournamentnew.data.viewModel.LocationViewModel
import com.example.tournamentnew.data.viewModel.NewTournamentViewModel
import com.example.tournamentnew.data.viewModel.OrganiserDashBoardViewModel
import com.example.tournamentnew.data.viewModel.PlayerDashBoardViewModel
import com.example.tournamentnew.ui.home.DisplayTournamentScreen
import com.example.tournamentnew.ui.home.HomeScreen
import com.example.tournamentnew.ui.home.JoinTournamentScreen
import com.example.tournamentnew.ui.home.LoginRoute
import com.example.tournamentnew.ui.home.LoginScreen
import com.example.tournamentnew.ui.home.NewTournamentScreen
import com.example.tournamentnew.ui.home.OrganiserDashBoard
import com.example.tournamentnew.ui.home.PlayerDashBoard
import com.example.tournamentnew.ui.home.SignUpScreen
import com.example.tournamentnew.ui.home.User
import com.example.tournamentnew.ui.maps.FetchUserLocationScreen

@Composable
fun AppNavComponent(modifier : Modifier = Modifier ) {
    var navController = rememberNavController()
    val locationViewModel:LocationViewModel = viewModel()
    val playerDashBoardViewModel:PlayerDashBoardViewModel = viewModel()
    val organiserDashBoardViewModel: OrganiserDashBoardViewModel = viewModel()
    val newTournamentViewModel: NewTournamentViewModel = viewModel()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController,modifier = modifier)
        }
        composable("userlogin/{userType}") {backStackEntry ->
            val userType:String = backStackEntry.arguments?.getString("userType")?:"admin"
            LoginScreen(navController,userType=userType, loginViewModel = viewModel(), playerViewModel = playerDashBoardViewModel
            , organiserViewModel = organiserDashBoardViewModel)
        }
        composable("usersignup/{userType}") {backStackEntry ->
            val userType:String = backStackEntry.arguments?.getString("userType")?:"admin"
            SignUpScreen(navController,locationViewModel = locationViewModel,userType = userType)
        }
        composable("fetchlocation") {
            FetchUserLocationScreen(navController,locationViewModel)
        }
        composable("playerdashboard") {
            PlayerDashBoard(navController
            ,playerDashBoardViewModel = playerDashBoardViewModel)
        }
        composable("organiserdashboard") {
            OrganiserDashBoard(navController
            ,organiserDashBoardViewModel = organiserDashBoardViewModel)
        }
        composable("newtournament") {
            NewTournamentScreen(navController,organiserDashBoardViewModel= organiserDashBoardViewModel,locationViewModel = locationViewModel)
        }
        composable("jointournament") {
            JoinTournamentScreen(navController,playerDashBoardViewModel = playerDashBoardViewModel,locationViewModel=locationViewModel)
        }
        composable("displaytournament") {
            DisplayTournamentScreen(navController,playerDashBoardViewModel)
        }
    }

}