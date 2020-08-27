package com.moeiny.reza.projectfactorysample.repository

import android.app.Application
import android.os.AsyncTask
import android.view.View
import com.moeiny.reza.optustest.repository.database.entitiy.DecisionEntity
import com.moeiny.reza.projectfactorysample.repository.model.Decision
import com.moeiny.reza.projectfactorysample.repository.model.database.AppDatabase
import com.moeiny.reza.projectfactorysample.repository.model.database.dao.DecisionDao
import com.moeiny.reza.projectfactorysample.repository.retrofit.ApiClient
import com.moeiny.reza.projectfactorysample.repository.retrofit.ApiService
import com.moeiny.reza.projectfactorysample.view.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DecisionsRepository(application: Application){

    private  var decisionDao: DecisionDao

    private  var allDecisionData:List<DecisionEntity>

    init {
        val db: AppDatabase = AppDatabase.getInstance(
            application.applicationContext )!!

        decisionDao = db.DecissionDao()

        allDecisionData = decisionDao.getAll()

    }

    //////////////// implementing Function to Access ِDecision Details on Database//////////

    fun insertDecision(decisionEntity: DecisionEntity){
        DecisionInsert(decisionDao).execute(decisionEntity)
    }

    fun updateDecision(decisionEntity: DecisionEntity){
        DecisionUpdate(decisionDao).execute(decisionEntity)
    }

    fun deleteDecision(decisionEntity: DecisionEntity){
        DecisionDelete(decisionDao).execute(decisionEntity)
    }

    fun deleteAllDecisions(){
        decisionDao.deleteAll()
    }

    fun findDecisionByName(name:String):DecisionEntity{
        return decisionDao.findDecissionByName(name)
    }

    fun getAllDecisions():List<DecisionEntity>{
        return allDecisionData
    }

    fun getSelected():List<DecisionEntity>{
        return decisionDao.getSelected()
    }

    private class DecisionInsert(decisionDao: DecisionDao): AsyncTask<DecisionEntity, Void, Void>(){

        private var decisionDao: DecisionDao
        init{
            this.decisionDao=decisionDao
        }

        override fun doInBackground(vararg p0: DecisionEntity): Void? {
            decisionDao.insert(p0[0])
            return null
        }

    }

    private class DecisionUpdate(decisionDao: DecisionDao): AsyncTask<DecisionEntity, Void, Void>(){

        private var decisionDao:DecisionDao
        init{
            this.decisionDao=decisionDao
        }

        override fun doInBackground(vararg p0: DecisionEntity): Void? {
            decisionDao.update(p0[0])
            return null
        }
    }

    private class DecisionDelete(decisionDao: DecisionDao): AsyncTask<DecisionEntity, Void, Void>(){

        private var decisionDao:DecisionDao
        init{
            this.decisionDao=decisionDao
        }

        override fun doInBackground(vararg p0: DecisionEntity): Void? {
            decisionDao.delete(p0[0])
            return null
        }
    }


    //////////////Implementing API methods to get data from API and save in database//////////////////////////////

    fun getDecisions(){

        var apiClient= ApiClient()
        var call: Call<List<Decision>> =apiClient.getClient().create(ApiService::class.java).getDecisonsInfo()

        call.enqueue(object : Callback<List<Decision>> {

            override fun onFailure(call: Call<List<Decision>>, t: Throwable) {
                //   Toast.makeText(context,t.toString(), Toast.LENGTH_SHORT).show()
                var t=1
            }

            override fun onResponse(call: Call<List<Decision>>, response: Response<List<Decision>>) {
                var decisions:List<Decision>?=response!!.body()
                var resultList = ArrayList<Decision>()
                resultList=decisions!! as ArrayList<Decision>
                /*
                * In this section :
                * 1 - All the Movie details fetch from Api
                * 2- Create a filmEntity model for each film and save in the Database
                */
                for (i in 0..resultList.size-1) {
                    var decision = DecisionEntity(resultList[i].id, resultList[i].title,resultList[i].selected)

                    if (findDecisionByName(resultList[i].title) == null)
                        insertDecision(decision)
                }

                var view= HomeActivity.getView()
                view.visibility= View.INVISIBLE
                HomeActivity.move()
            }

        })
    }



}




