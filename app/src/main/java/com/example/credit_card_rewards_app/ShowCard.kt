package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button

@Composable
fun ShowCard(cardList: List<String>){
    Column(
        modifier = Modifier
        .fillMaxSize()
    ){
        for (card in cardList){
            Button(onClick={}){
                Text(text = card)
            }
        }
    }

}