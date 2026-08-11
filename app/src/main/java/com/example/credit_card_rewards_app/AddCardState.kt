package com.example.credit_card_rewards_app

data class AddCardState(
    val cardName: String = "",
    val rewardNames: List<String> = emptyList(),
    val rewardMap: Map<String, String> = emptyMap(),
    val cardId: Int = 0,
    val addNewCategory: Boolean = false,
    val newCategory: String = "",
)
