package com.example.credit_card_rewards_app

data class UpdateCardState(
    val card: Card = Card(name=""),
    val rewardsList: MutableList<Reward> = mutableListOf(),
    val toUpdateRewardMap: MutableMap<Int, String> = mutableMapOf()
)
