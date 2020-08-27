package com.moeiny.reza.optustest.repository.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Decisions")
class DecisionEntity (@PrimaryKey   var decision_id:Int,
                      @ColumnInfo var decision_title: String,
                      @ColumnInfo var decision_select: Int
)

