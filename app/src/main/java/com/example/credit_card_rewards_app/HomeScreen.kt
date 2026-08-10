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
fun Parent(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Greeting()

        Button(onClick = {navController.navigate("AddCard")}){Text(text = "Add Card", fontSize = 16.sp)}

        Button(onClick = {}){Text(text = "Best Reward Value", fontSize = 16.sp)}

        Button(onClick = {}){Text(text = "Update Card", fontSize = 16.sp)}
    }
}

@Composable
fun HomeScreen(navController: NavController){
    Parent(navController)
}