package com.example.credit_card_rewards_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    val cardDao = db.cardDao
    val rewardDao = db.rewardDao
    val rewardNamesDao = db.rewardNamesDao

    private val _state = MutableStateFlow(BestCardState())
    val state: StateFlow<BestCardState> = _state

    fun getRewardNames() {
        viewModelScope.launch {
            _state.update {
                it.copy(rewardNames = rewardNamesDao.getAllRewardNames())
            }
        }
    }


    fun getBest(rewardsNames: String) {
        viewModelScope.launch {
            val cardIds = rewardDao.getMaxValue(rewardsNames)
            _state.update {
                it.copy(bestCards = cardDao.selectCard(cardIds))
            }
        }
    }
}