package com.droidtechlab.shaaditestui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidtechlab.shaaditestui.data.db.dao.ShaadiDao
import com.droidtechlab.shaaditestui.data.db.entities.ShaadiMatchListEntity
import com.droidtechlab.shaaditestui.data.db.entities.converters.Converters

const val DB_VERSION = 1
const val TABLE_SHAADI_MATCH_ENTITY = "table_shaadi_match_entity"

@Database(entities = [ShaadiMatchListEntity::class], version = DB_VERSION)
@TypeConverters(Converters::class)
abstract class DB : RoomDatabase() {
    abstract fun shaadiMatchListDao(): ShaadiDao
}