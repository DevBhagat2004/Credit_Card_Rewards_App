package com.example.credit_card_rewards_app

data class AddCardState(
    var cardName: String = "",
    val rewardNames: MutableList<String> = mutableListOf(),
    var rewardMap: MutableMap<String, String> = mutableMapOf(),
    val cardId: Int = 0
)
