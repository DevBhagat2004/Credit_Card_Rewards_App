package com.example.credit_card_rewards_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application)  {
    private val db = AppDatabase.getDatabase(application)
    val cardDao = db.cardDao
    val rewardDao = db.rewardDao

    fun getBest (RewardsNames: String){
        viewModelScope.launch {
            val cardIds = rewardDao.getMaxValue(RewardsNames)
            cardDao.selectCard(cardIds)
        }
    }
}