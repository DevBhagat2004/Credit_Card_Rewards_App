package com.example.credit_card_rewards_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UpdateCardViewModel(application: Application): AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)

    val cardDao = db.cardDao
    val rewardDao = db.rewardDao
    val rewardNamesDao = db.rewardNamesDao

    private val _state = MutableStateFlow(UpdateCardState())

    val state : MutableStateFlow<UpdateCardState> = _state

    fun getCard(cardId: Int){
        viewModelScope.launch{
            _state.update{
                it.copy(card = db.cardDao.getOneCard(cardId))
            }
        }
    }

    fun getRewards(cardId: Int){
        viewModelScope.launch{
            _state.update{
                it.copy(rewardsList = rewardDao.getCardRewards(cardId))
            }
        }
    }

    fun updateCardName(newName: String){
        viewModelScope.launch{
            _state.update{
                it.copy(card = it.card.copy(name = newName))
            }
        }
    }

    fun insertUpdatedCard(card: Card){
        viewModelScope.launch{
            cardDao.upsertCard(card)
        }
    }

    fun deleteCardandRewards(card: Card){
        viewModelScope.launch{
           val id  = card.cardId

            cardDao.deleteCard(card)

            rewardDao.deleteRewardsByCardId(id)
        }
    }

    fun fillUpdateRewardMap(rewardId: Int, newValue: String){
        _state.update{
            val newMap = it.toUpdateRewardMap.toMutableMap()
            newMap[rewardId] = newValue
            it.copy(toUpdateRewardMap = newMap)
        }
    }

    fun updateRewards(updatedValues: Map<Int, String>){
        viewModelScope.launch {
            for ((k,v ) in updatedValues){
                val oldReward = rewardDao.getRewardByRewardId(k)
                val value = v.toDoubleOrNull() ?: 0.0
                val newReward = oldReward.copy(
                    rewardValue = value
                )
                rewardDao.upsertReward(newReward)
            }
        }
    }
}