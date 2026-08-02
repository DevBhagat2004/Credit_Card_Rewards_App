package com.example.credit_card_rewards_app
import androidx.room.RoomDatabase
import androidx.room.Database

@Database(
    entities = [Card::class, Reward::class],
    version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract val cardDao: Cards_Dao
    abstract val rewardDao: Rewards_Dao
}