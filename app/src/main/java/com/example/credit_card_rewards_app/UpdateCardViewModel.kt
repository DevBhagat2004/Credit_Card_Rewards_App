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

    fun fillUpdateRewardMap( newRewardValue: MutableMap<Int, String>){
        viewModelScope.launch{
            _state.update{
                it.copy(toUpdateRewardMap = newRewardValue)
            }
        }
    }

    fun updateRewards(updatedValues: MutableMap<Int, String>){
        for ((k,v ) in updatedValues){
            viewModelScope.launch {
                val oldReward = rewardDao.getRewardByRewardId(k)

                val newReward = oldReward.copy(
                    rewardValue = v.toDouble()
                )

                rewardDao.upsertReward(newReward)
            }
        }
    }

}