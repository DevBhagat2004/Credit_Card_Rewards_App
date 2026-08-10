package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AddCard(navController: NavController){
    val addCardViewModel: AddCardViewModel = viewModel()
    val addCardUIState by addCardViewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        addCardViewModel.getRewardNames()
    }

    LaunchedEffect(addCardUIState.rewardNames) {
        addCardViewModel.makeRewardMap(addCardUIState.rewardNames)
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = "Please Enter Card Name")
        TextField (
            value = addCardUIState.cardName,
            onValueChange = {
                addCardViewModel.addCardName(it)
            }
        )

        Text(text = "Enter values for given category leave blank if not valid")

        for ( (k,v) in addCardUIState.rewardMap){
            Row() {
                Text(text = k)

                TextField(
                    value = v,
                    onValueChange = {
                        val newMap: MutableMap<String, String> = addCardUIState.rewardMap.toMutableMap()
                        newMap[k] = it
                        addCardViewModel.updateRewardMap(newMap)
                    }
                )
            }
        }
        Button(onClick= {addCardViewModel.addCardandRewards(addCardUIState.cardName, addCardUIState.rewardMap)
        navController.navigate("HomeScreen")
        }){
            Text(text = "Submit",
                fontSize = 16.sp)
        }
    }
}


