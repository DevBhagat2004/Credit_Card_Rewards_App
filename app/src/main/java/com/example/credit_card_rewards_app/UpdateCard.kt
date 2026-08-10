package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun UpdateCard(navController: NavController, cardId: Int){

    val updateCardViewModel: UpdateCardViewModel = viewModel()
    val updateCardUIState by updateCardViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(cardId) {
        updateCardViewModel.getCard(cardId)
        updateCardViewModel.getRewards(cardId)
    }

    Column(modifier = Modifier
        .fillMaxSize()
    ){
        Text(text="Change the values in textfield if you want to change it")
        Text(text = "CardName: ")
        TextField(
            value = updateCardUIState.card.name,
            onValueChange = {updateCardViewModel.updateCardName(it)}
        )

        for(reward in updateCardUIState.rewardsList){
            Row(){
                Text(text = "Reward: "+reward.rewardCategory)
                val currentText = updateCardUIState.toUpdateRewardMap[reward.rewardId] ?: reward.rewardValue.toString()
                TextField(
                    value = currentText,
                    onValueChange = { newValue ->
                        updateCardViewModel.fillUpdateRewardMap(reward.rewardId, newValue)
                    }
                )
            }
        }

        Button(onClick={
            updateCardViewModel.deleteCardandRewards(updateCardUIState.card)
            navController.popBackStack()
        }){
            Text(text = "Delete Card")
        }

        Button(onClick={
            updateCardViewModel.insertUpdatedCard(updateCardUIState.card)
            updateCardViewModel.updateRewards(updateCardUIState.toUpdateRewardMap)
            navController.popBackStack()
        }){
            Text(text = "UpdateCard")
        }
    }

}
