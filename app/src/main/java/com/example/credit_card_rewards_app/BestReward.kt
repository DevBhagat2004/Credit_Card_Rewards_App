package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button

@Composable
fun BestReward(RewardList: List<String>){
    Column(){
            for (Reward in RewardList){
                Button(onClick={}){
                    Text(text = Reward)
                }
            }
    }
}