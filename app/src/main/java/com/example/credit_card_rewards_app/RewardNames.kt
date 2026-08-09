package com.example.credit_card_rewards_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RewardNames(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)
