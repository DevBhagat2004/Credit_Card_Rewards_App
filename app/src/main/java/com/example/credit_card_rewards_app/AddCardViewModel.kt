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

    init {
        viewModelScope.launch {
            rewardNamesDao.getAllRewardNames().collect { names ->
                _state.update { currentState ->
                    // Initialize rewardMap only if it's currently empty
                    val newRewardMap = if (currentState.rewardMap.isEmpty()) {
                        val holdRewardMap = mutableMapOf<String, String>()
                        for (name in names) {
                            holdRewardMap[name] = ""
                        }
                        holdRewardMap
                    }
                    else if (currentState.newCategory!=""&&!currentState.rewardMap.containsKey(currentState.newCategory)) {
                        val holdRewardMap = currentState.rewardMap.toMutableMap()
                        holdRewardMap[currentState.newCategory] = ""
                        holdRewardMap
                    }
                    else {
                        currentState.rewardMap
                    }
                        currentState.copy(
                            rewardNames = names,
                            rewardMap = newRewardMap
                        )
                }
            }
        }
    }

    fun onNameChange(name: String){
        viewModelScope.launch{
            _state.update{
                it.copy(cardName = name)
            }
        }
    }

    fun onRewardValueChange(newMap: MutableMap<String, String>, categoryName: String, categoryValue: String){
        if(categoryName=="General"){
            for (k in newMap.keys.toList()){
                if (k!="General"&&newMap[k]==""){
                    newMap[k]= categoryValue
                }
            }
        }
        _state.update{
            it.copy(rewardMap = newMap)
        }
    }

    fun fillRewardCategory(name: String){
        viewModelScope.launch {
            _state.update {
                it.copy(newCategory = name)
            }
        }
    }
    fun onNewCategoryAddition(newCategory: String, oldRewardMap: Map<String, String>){
        viewModelScope.launch{
            val newReward: RewardNames = RewardNames(name = newCategory)
            val newRewardMap: MutableMap<String, String> = oldRewardMap.toMutableMap()
            newRewardMap[newCategory] = ""
            rewardNamesDao.insertNames(newReward)
            _state.update{
                it. copy(rewardMap = newRewardMap,
                          addNewCategory = false,
                           newCategory = ""
                    )
            }
        }
    }

    fun onAddNewCategoryClick(){
        _state.update{
           it.copy( addNewCategory = true)
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