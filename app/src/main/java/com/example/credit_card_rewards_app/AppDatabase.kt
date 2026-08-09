package com.example.credit_card_rewards_app
import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Card::class, Reward::class, RewardNames::class],
    version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract val cardDao: Cards_Dao
    abstract val rewardDao: Rewards_Dao

    abstract val rewardNamesDao: RewardNames_Dao

    companion object{
        @Volatile // To ensure all reads are same
        private var INSTANCE: AppDatabase ?= null

        fun getDatabase(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "credit_card_rewards_db"
                )
                    .addCallback(object: RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase){
                            super.onCreate(db)

                            CoroutineScope(Dispatchers.IO).launch{
                                val database = getDatabase(context)
                                val myList = listOf("Gas", "Grocery", "Dining", "Online")

                                for (myname in myList) {
                                    val rewardName: RewardNames = RewardNames(name= myname)
                                    database.rewardNamesDao.insertNames(rewardName)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance // This assignment is for future calls
                instance // In kotlin whatever is in the last line is returned
            }
        }
    }
}