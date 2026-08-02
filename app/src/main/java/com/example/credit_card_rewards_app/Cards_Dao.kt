package com.example.credit_card_rewards_app
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface Cards_Dao {
    @Upsert
    suspend fun upsertCard(card: Card)

    @Delete
    suspend fun deleteCard(card: Card)

    @Query ("SELECT * from Card WHERE cardId IN (:ids)")
    suspend fun selectCard(ids: List<Int>): List<Card>
}