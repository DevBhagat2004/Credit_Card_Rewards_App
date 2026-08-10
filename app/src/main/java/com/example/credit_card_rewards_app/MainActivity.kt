package com.example.credit_card_rewards_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.credit_card_rewards_app.ui.theme.Credit_Card_Rewards_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "HomeScreen", builder = {
                    composable("HomeScreen") {HomeScreen(navController)}
                    composable("AddCard"){AddCard(navController)}
                    composable("ShowCard") {ShowCard(navController)}
                    composable("BestReward") {BestReward(navController)}
                    composable(
                        "UpdateCard/{cardId}",
                        arguments = listOf(navArgument("cardId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val cardId = backStackEntry.arguments?.getInt("cardId") ?: 0
                        UpdateCard(navController, cardId)
                    }
            })
        }
    }
}