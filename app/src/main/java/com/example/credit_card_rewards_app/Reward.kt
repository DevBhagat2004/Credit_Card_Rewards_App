package com.example.credit_card_rewards_app
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reward (
    @PrimaryKey(autoGenerate = true)
    val rewardId:Int = 0,
    val cardId: Int,
    val rewardCategory: String,
    val rewardValue: Double
)