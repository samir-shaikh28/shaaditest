package com.droidtechlab.shaaditestui.data.db.dao

import androidx.room.*
import com.droidtechlab.shaaditestui.data.db.DBHelper
import com.droidtechlab.shaaditestui.data.db.ID
import com.droidtechlab.shaaditestui.data.db.MATCH_STATE
import com.droidtechlab.shaaditestui.data.db.TABLE_SHAADI_MATCH_ENTITY
import com.droidtechlab.shaaditestui.data.db.entities.ShaadiMatchListEntity
import com.droidtechlab.shaaditestui.data.models.MatchesList

@Dao
interface ShaadiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchItem(matchEntity: ShaadiMatchListEntity)

    @Query("UPDATE $TABLE_SHAADI_MATCH_ENTITY SET $MATCH_STATE = 1 WHERE $ID = :id")
    fun setAccepted(id: Long)

    @Query("UPDATE $TABLE_SHAADI_MATCH_ENTITY SET $MATCH_STATE = 0 WHERE $ID = :id")
    fun setRejected(id: Long)

    @Query("SELECT * FROM $TABLE_SHAADI_MATCH_ENTITY")
    fun returnMatchList(): List<ShaadiMatchListEntity>


    @Query("DELETE FROM $TABLE_SHAADI_MATCH_ENTITY")
    fun clearMatchList()

    @Transaction
    fun clearAndInsertMatchList(matchEntity: List<MatchesList>?) {
        clearMatchList()
        matchEntity?.forEach { entity ->
            insertMatchItem(DBHelper.toMatchListEntity(entity))
        }
    }

}