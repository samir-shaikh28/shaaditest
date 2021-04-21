package com.droidtechlab.shaaditestui.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidtechlab.shaaditestui.data.db.ID
import com.droidtechlab.shaaditestui.data.db.MATCH_STATE
import com.droidtechlab.shaaditestui.data.db.TABLE_SHAADI_MATCH_ENTITY
import com.droidtechlab.shaaditestui.data.db.entities.ShaadiMatchListEntity

@Dao
interface ShaadiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchItem(matchEntity: ShaadiMatchListEntity)

    @Query("UPDATE $TABLE_SHAADI_MATCH_ENTITY SET $MATCH_STATE = 1 WHERE $ID = :id")
    fun setAccepted(id: Long)

    @Query("UPDATE $TABLE_SHAADI_MATCH_ENTITY SET $MATCH_STATE = 2 WHERE $ID = :id")
    fun setRejected(id: Long)


}