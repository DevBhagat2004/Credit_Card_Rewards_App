package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    // Get the card and its rewards from db
    updateCardViewModel.getCard(cardId)
    updateCardViewModel.getRewards(cardId)

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
                TextField(
                    value = reward.rewardValue.toString(),
                    onValueChange = {
                        val newMap: MutableMap<Int, String> = updateCardUIState.toUpdateRewardMap
                        newMap[reward.rewardId] = it
                        updateCardViewModel.fillUpdateRewardMap(newMap)
                    }
                )
            }
        }

        Button(onClick={updateCardViewModel.deleteCardandRewards(updateCardUIState.card)}){
            Text(text = "Delete Card")
        }

        Button(onClick={updateCardViewModel.insertUpdatedCard(updateCardUIState.card)
                        updateCardViewModel.updateRewards(updateCardUIState.toUpdateRewardMap)
        }){
            Text(text = "UpdateCard")
        }
    }

}