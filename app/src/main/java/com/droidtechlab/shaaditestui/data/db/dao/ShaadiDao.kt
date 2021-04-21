package com.droidtechlab.shaaditestui.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.droidtechlab.shaaditestui.data.db.entities.ShaadiMatchListEntity

@Dao
interface ShaadiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchItem(matchEntity: ShaadiMatchListEntity)

}