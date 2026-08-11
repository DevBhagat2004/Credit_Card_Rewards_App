package com.example.credit_card_rewards_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BestRewardViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    val cardDao = db.cardDao
    val rewardDao = db.rewardDao
    val rewardNamesDao = db.rewardNamesDao

    private val _state = MutableStateFlow(BestRewardState())
    val state: StateFlow<BestRewardState> = _state

    init {
        viewModelScope.launch {
            rewardNamesDao.getAllRewardNames().collect { names ->
                _state.update { it.copy(rewardNames = names) }
            }
        }
    }


    fun selectCategory(category: String) {
        viewModelScope.launch {
            val cardIds = rewardDao.getMaxValue(category)
            _state.update {
                it.copy(
                    selectedCategory = category,
                    recommendedCards = cardDao.selectCard(cardIds)
                )
            }
        }
    }
}
