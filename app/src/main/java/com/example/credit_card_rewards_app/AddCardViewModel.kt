package com.example.credit_card_rewards_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddCardViewModel(application: Application): AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    val cardDao = db.cardDao
    val rewardDao = db.rewardDao
    val rewardNamesDao = db.rewardNamesDao
    private val _state = MutableStateFlow(AddCardState())

    val state : StateFlow<AddCardState> = _state


    fun onNameChange(name: String){
        viewModelScope.launch{
            _state.update{
                it.copy(cardName = name)
            }
        }
    }

    fun initializeData() {
        viewModelScope.launch {
            val names = rewardNamesDao.getAllRewardNames()
            val holdRewardMap = mutableMapOf<String, String>()
            for (name in names) {
                holdRewardMap[name] = "0.0"
            }
            _state.update {
                it.copy(
                    rewardNames = names,
                    rewardMap = holdRewardMap
                )
            }
        }
    }

    fun onRewardValueChange(newMap: Map<String, String>){
        _state.update{
            it.copy(rewardMap = newMap.toMutableMap())
        }
    }

    fun saveCard(cardName: String, rewardMap: Map<String, String>){
        val card = Card(name = cardName)
        viewModelScope.launch{
           val cardId = cardDao.insertCard(card).toInt()

            _state.update{it.copy(cardId = cardId)}

            for( (k,v) in rewardMap){
                val value = v.toDoubleOrNull() ?: 0.0
                val reward = Reward(cardId = cardId, rewardCategory = k, rewardValue = value)

                rewardDao.insertReward(reward)
            }
        }
    }

}