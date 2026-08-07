package com.example.credit_card_rewards_app
import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room

@Database(
    entities = [Card::class, Reward::class],
    version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract val cardDao: Cards_Dao
    abstract val rewardDao: Rewards_Dao

    companion object{
        @Volatile // To ensure all reads are same
        private var INSTANCE: AppDatabase ?= null

        fun getDatabase(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "credit_card_rewards_db"
                ).build()
                INSTANCE = instance // This assignment is for future calls
                instance // In kotlin whatever is in the last line is returned
            }
        }
    }
}