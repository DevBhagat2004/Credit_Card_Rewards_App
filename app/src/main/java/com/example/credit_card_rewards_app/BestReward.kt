package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BestReward( rewardList: List<String>) {
    val cardViewModel: CardViewModel = viewModel()
    val uiState by cardViewModel.state.collectAsStateWithLifecycle()
    cardViewModel.showCard()
    Column {
        for (reward in rewardList) {
            Button(onClick = { cardViewModel.getBest(reward) }) {
                Text(text = reward)
            }
        }
        for (card in uiState.bestCards) {
            Text(text = card.name)
        }
    }
}