package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun BestReward(navController: NavController) {
    val bestRewardViewModel: BestRewardViewModel = viewModel()
    val uiState by bestRewardViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        bestRewardViewModel.getRewardNames()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Best Rewards", fontSize = 24.sp)

        Text(text = "Select Category:")
        for (reward in uiState.rewardNames) {
            Button(onClick = { bestRewardViewModel.selectCategory(reward)}) {
                Text(text = reward)
            }
        }

        Button(onClick={navController.popBackStack()}){
            Text(text = "Back")
        }

        if (uiState.selectedCategory != null) {
            Text(text = "Selected: ${uiState.selectedCategory}", fontSize = 18.sp)
            Text(text = "Recommended Cards:")
            for (card in uiState.recommendedCards) {
                Text(text = card.name)
            }
        }



    }
}
