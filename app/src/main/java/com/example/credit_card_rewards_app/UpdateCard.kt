package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

@Composable
fun UpdateCard(cardId: Int){

    Column(modifier = Modifier
        .fillMaxSize()
    ){
        var newRewardValues = remember{
            mutableStateListOf<String>()
        }

        for((index,reward) in rewardList.withIndex()){
            Row(){
                Text(text = "Reward: "+reward.rewardCategory+" Value: "+reward.rewardValue.toString())
                TextField(
                    value = newRewardValues[index],
                    onValueChange = {newRewardValues[index]=it}
                )
            }
        }

        Button(onClick={}){
            Text(text = "Delete Card")
        }

        Button(onClick={}){
            Text(text = "UpdateCard")
        }
    }

}