package com.example.credit_card_rewards_app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RewardNames_Dao {
    @Insert
    suspend fun insertNames(names: Set<String>)

    @Insert
    suspend fun insertName(name: String)

    @Query("SELECT * FROM RewardNames")
    suspend fun getAllRewardNames() : MutableList <String>
}