package com.example.credit_card_rewards_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ViewModel(application: Application): AndroidViewModel(application)  {
    fun findBest (RewardsNames: List<String>){
        getMaxValue()
    }
}