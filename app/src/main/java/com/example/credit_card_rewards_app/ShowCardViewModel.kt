package com.example.credit_card_rewards_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShowCardViewModel (application: Application): AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)

    val cardDao = db.cardDao

    val rewardDao = db.rewardDao

    private val _state = MutableStateFlow(ShowCardState())

    val state : StateFlow<ShowCardState> = _state

    fun showCard (){
        viewModelScope.launch {
            _state.update{
                it.copy (cardList = cardDao.getAllCards())
            }
        }
    }

}