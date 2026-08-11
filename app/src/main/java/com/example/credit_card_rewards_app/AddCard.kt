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
        addCardViewModel.initializeData()
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = "Please Enter Card Name")
        TextField (
            value = addCardUIState.cardName,
            onValueChange = {
                addCardViewModel.onNameChange(it)
            }
        )

        Text(text = "Enter values for given category leave blank if not valid")

        for ( (k,v) in addCardUIState.rewardMap){
            Row() {
                Text(text = k)

                TextField(
                    value = v,
                    onValueChange = { newValue ->
                        val newMap = addCardUIState.rewardMap.toMutableMap()
                        newMap[k] = newValue
                        addCardViewModel.onRewardValueChange(newMap)
                    }
                )
            }
        }
        Button(onClick= {
            addCardViewModel.saveCard(addCardUIState.cardName, addCardUIState.rewardMap)
            navController.popBackStack()
        }){
            Text(text = "Submit",
                fontSize = 16.sp)
        }

        Button(onClick= {
            navController.popBackStack()
        }){
            Text(text = "Back",
                fontSize = 16.sp)
        }
    }
}


