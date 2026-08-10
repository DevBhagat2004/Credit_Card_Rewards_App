package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ShowCard(navController: NavController){
    val showCardViewModel: ShowCardViewModel = viewModel()
    val showCardUIState by showCardViewModel.state.collectAsStateWithLifecycle()
    
    LaunchedEffect(Unit) {
        showCardViewModel.showCard()
    }
    
    Column(
        modifier = Modifier
        .fillMaxSize()
    ){
        for (card in showCardUIState.cardList){
            Button(onClick={
                navController.navigate("UpdateCard/${card.cardId}")
            }){
                Text(text = card.name)
            }
        }
    }

}