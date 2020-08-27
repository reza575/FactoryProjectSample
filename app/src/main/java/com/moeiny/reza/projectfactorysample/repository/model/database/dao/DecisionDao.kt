package com.moeiny.reza.projectfactorysample.repository.model.database.dao

import androidx.room.*
import com.moeiny.reza.optustest.repository.database.entitiy.DecisionEntity


@Dao
interface DecisionDao {

    @Query("SELECT * FROM Decisions ORDER BY decision_id ")
    fun getAll(): List<DecisionEntity>

    @Query("SELECT * FROM Decisions WHERE decision_title = :name ")
    fun findDecissionByName(name: String): DecisionEntity

    @Query("SELECT * FROM Decisions WHERE decision_select = 1 ")
    fun getSelected(): List<DecisionEntity>

    @Query("DELETE FROM Decisions")
    fun deleteAll()

    @Insert
    fun insert(decision: DecisionEntity)

    @Update
    fun update(decision: DecisionEntity)

    @Delete
    fun delete(decision: DecisionEntity)
}




