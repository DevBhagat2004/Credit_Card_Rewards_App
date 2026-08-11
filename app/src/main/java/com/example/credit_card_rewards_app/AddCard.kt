package com.example.credit_card_rewards_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCard(navController: NavController) {
    val addCardViewModel: AddCardViewModel = viewModel()
    val state by addCardViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Card") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.cardName,
                onValueChange = { addCardViewModel.onNameChange(it) },
                label = { Text("Card Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Reward Values", style = MaterialTheme.typography.titleMedium)

            state.rewardMap.forEach { (category, value) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(category, modifier = Modifier.weight(1f))
                    OutlinedTextField(
                        value = value,
                        onValueChange = { newValue ->
                            val newMap = state.rewardMap.toMutableMap()
                            newMap[category] = newValue
                            addCardViewModel.onRewardValueChange(newMap)
                        },
                        label = { Text("Value") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
            }

            if (state.addNewCategory){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = state.newCategory,
                        onValueChange = { newValue ->
                            addCardViewModel.fillRewardCategory(newValue)
                        },
                        label = { Text("Category") },
                        modifier = Modifier.weight(1f),
                    )
                }
                Button(
                    onClick = {
                        addCardViewModel.onNewCategoryAddition(state.newCategory, state.rewardMap)
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) {
                    Text("Add")
                }
            }

            Button(
                onClick = {
                    addCardViewModel.saveCard(state.cardName, state.rewardMap)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text("Save Card")
            }

            Button(
                onClick = {
                    addCardViewModel.onAddNewCategoryClick()
                },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text("Add New Category")
            }
        }
    }
}


