package com.example.credit_card_rewards_app

data class BestCardState(
    val bestCards: List<Card> = emptyList(),
    val rewardsList: MutableList<Reward> = mutableListOf(),
    val rewardNames: List<String> = emptyList()
)
