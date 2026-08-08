package com.example.credit_card_rewards_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.credit_card_rewards_app.ui.theme.Credit_Card_Rewards_AppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp


@Composable
fun AddCard(rewardCategories: List<String>){

    val rewardValues = remember{
        mutableStateListOf<String>()
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(text = "Enter values for given category leave blank if not valid")

        for ((index,category) in rewardCategories.withIndex()){
            Row() {
                Text(text = category)

                TextField(
                    value = rewardValues[index],
                    onValueChange = { rewardValues[index] = it}
                )
            }


        Button(onClick= {}){
            Text(text = "Submit",
                 fontSize = 16.sp)
            }

        }
    }
}


