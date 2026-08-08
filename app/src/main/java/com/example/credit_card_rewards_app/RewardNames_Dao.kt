package com.example.credit_card_rewards_app

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface RewardNames_Dao {
    @Insert
    suspend fun insertNames(names: Set<String>)

    @Insert
    suspend fun insertName(name: String)
}