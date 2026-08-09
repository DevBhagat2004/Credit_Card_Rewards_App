package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Greeting(){
    Text(text = "Please select one option",
        color = Color.Blue,
        fontSize = 30.sp)

}

@Composable
fun Options(buttonText: String){
    Button(
        onClick = {},
    ){
        Text(
            text = buttonText,
            fontSize = 16.sp
        )
    }
}

@Composable
fun Parent(){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Greeting()

        Options("Add Card")

        Options("Best Reward Value")

        Options("Update Card")
    }
}

@Composable
fun HomeScreen(navController: NavController){
    Parent()
}