package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Card Rewards") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Manage your card rewards effectively",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            ElevatedButton(
                onClick = { navController.navigate("AddCard") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Add New Card")
            }

            ElevatedButton(
                onClick = { navController.navigate("BestReward") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Best Reward Value")
            }

            ElevatedButton(
                onClick = { navController.navigate("ShowCard") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Update Card")
            }
        }
    }
}
