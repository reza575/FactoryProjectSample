package com.moeiny.reza.projectfactorysample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.moeiny.reza.optustest.repository.database.entitiy.DecisionEntity
import com.moeiny.reza.projectfactorysample.repository.DecisionsRepository
import com.moeiny.reza.projectfactorysample.repository.model.Decision


class DecisionViewModel(application: Application) : AndroidViewModel(application) {

    private  var decisionRepository: DecisionsRepository
    private  var allDecisionData:List<DecisionEntity>
    var decisionList = ArrayList<Decision>()

    init {
        decisionRepository= DecisionsRepository(application)
        allDecisionData=decisionRepository.getAllDecisions()
    }


     fun setDecisionList(decisionList:List<Decision>){
        this.decisionList = decisionList as ArrayList<Decision>
    }

     fun getDecisionList():List<Decision>{
        return(decisionList)
    }

    //////////////// implementing Function to Access Decision on Database//////////

    fun insertDecision(decisionEntity: DecisionEntity){
        decisionRepository.insertDecision(decisionEntity)
    }

    fun updateDecision(decisionEntity: DecisionEntity){
        decisionRepository.updateDecision(decisionEntity)
    }

    fun deleteDecision(decisionEntity: DecisionEntity){
        decisionRepository.deleteDecision(decisionEntity)
    }

    fun deleteAllDecisions(){
        decisionRepository.deleteAllDecisions()
    }

    fun findDecisionByName(name: String):DecisionEntity{
        return decisionRepository.findDecisionByName(name)
    }

    fun getAllDecisions():List<DecisionEntity>{
        return allDecisionData
    }

    fun getSelected():List<DecisionEntity>{
        return decisionRepository.getSelected()
    }

//////////////Calling API methods//////////////////////////////

    fun getDecision() {
        decisionRepository.getDecisions()
    }

 }