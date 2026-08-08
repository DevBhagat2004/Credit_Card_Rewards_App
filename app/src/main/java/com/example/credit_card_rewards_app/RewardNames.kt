package com.example.credit_card_rewards_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RewardNames(
    val rewardNamesSet: MutableSet<String> = mutableSetOf<String>()
)
