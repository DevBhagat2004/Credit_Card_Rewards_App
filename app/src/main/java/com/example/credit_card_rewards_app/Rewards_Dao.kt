package com.example.credit_card_rewards_app
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import androidx.room.Query

@Dao
interface Rewards_Dao {
    @Upsert
    suspend fun upsertReward(reward: Reward)

    @Delete
    suspend fun deleteReward(reward: Reward)

    @Query("SELECT cardId FROM Reward WHERE rewardCategory = :category AND rewardValue = (SELECT MAX(rewardValue) FROM Reward WHERE rewardCategory = :category)")
    suspend fun getMaxValue(category: String): List<Int>
}
