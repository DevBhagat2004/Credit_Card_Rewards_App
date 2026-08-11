package com.example.credit_card_rewards_app

data class BestRewardState(
    val recommendedCards: List<Card> = emptyList(),
    val rewardNames: List<String> = emptyList(),
    val selectedCategory: String? = null
)
