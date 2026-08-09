package com.example.credit_card_rewards_app
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Upsert
import androidx.room.Query

@Dao
interface Rewards_Dao {

    @Insert
    suspend fun insertReward(reward: Reward)

    @Upsert
    suspend fun upsertReward(reward: Reward)

    @Delete
    suspend fun deleteReward(reward: Reward)

    @Query("DELETE FROM Reward WHERE cardId = (:cardId)")
    suspend fun deleteRewardsByCardId(cardId: Int)
    @Query("SELECT  * FROM Reward WHERE cardId = (:cardId)")
    suspend fun getCardRewards(cardId: Int): MutableList<Reward>

    @Query("SELECT cardId FROM Reward WHERE rewardCategory = :category AND rewardValue = (SELECT MAX(rewardValue) FROM Reward WHERE rewardCategory = :category)")
    suspend fun getMaxValue(category: String): List<Int>

    @Query("SELECT * FROM Reward WHERE rewardId = (:rewardId)")
    suspend fun getRewardByRewardId(rewardId: Int): Reward
}
