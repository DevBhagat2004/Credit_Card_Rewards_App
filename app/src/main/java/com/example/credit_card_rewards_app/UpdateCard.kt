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
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.LaunchedEffect
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
fun UpdateCard(navController: NavController, cardId: Int) {
    val updateCardViewModel: UpdateCardViewModel = viewModel()
    val state by updateCardViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(cardId) {
        updateCardViewModel.getCard(cardId)
        updateCardViewModel.getRewards(cardId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Update Card") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        updateCardViewModel.deleteCard(state.card)
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
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
                value = state.card.name,
                onValueChange = { updateCardViewModel.onNameChange(it) },
                label = { Text("Card Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Reward Values", style = MaterialTheme.typography.titleMedium)

            state.rewardsList.forEach { reward ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(reward.rewardCategory, modifier = Modifier.weight(1f))
                    val currentText = state.toUpdateRewardMap[reward.rewardId] ?: reward.rewardValue.toString()
                    OutlinedTextField(
                        value = currentText,
                        onValueChange = { newValue ->
                            updateCardViewModel.onRewardValueChange(reward.rewardId, newValue)
                        },
                        label = { Text("Value") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
            }

            Button(
                onClick = {
                    updateCardViewModel.saveChanges(state.card, state.toUpdateRewardMap)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text("Update Card")
            }
        }
    }
}
