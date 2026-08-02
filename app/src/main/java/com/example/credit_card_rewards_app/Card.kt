package com.example.credit_card_rewards_app
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card (
    @PrimaryKey(autoGenerate = true)
    val cardId: Int = 0,
    val name: String
)