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


    fun addCardName(name: String){
        viewModelScope.launch{
            _state.update{
                it.copy(cardName = name)
            }
        }
    }

    fun getRewardNames(){
        viewModelScope.launch {
            _state.update {
                it.copy(rewardNames = rewardNamesDao.getAllRewardNames())
            }
        }
    }

    fun makeRewardMap(rewardNames: List<String>){
        val holdRewardMap = mutableMapOf<String, String>()

        for(name in rewardNames){
            holdRewardMap[name] = "0.0"
        }
        viewModelScope.launch{
            _state.update{
                it.copy(rewardMap = holdRewardMap)
            }
        }
    }

    fun updateRewardMap(newMap: MutableMap<String, String>){
        viewModelScope.launch{
            _state.update{
                it.copy(rewardMap = newMap)
            }
        }
    }

    fun addCardandRewards(cardName: String, rewardMap: MutableMap<String, String>){
        val card = Card(name = cardName)
        viewModelScope.launch{
           val cardId = cardDao.insertCard(card).toInt()

            _state.update{it.copy(cardId = cardId)}

            for( (k,v) in rewardMap){
               val reward = Reward(cardId = cardId, rewardCategory = k, rewardValue = v.toDouble())

                rewardDao.insertReward(reward)
            }
        }
    }

}