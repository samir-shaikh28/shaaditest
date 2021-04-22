package com.droidtechlab.shaaditestui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidtechlab.shaaditestui.data.db.dao.ShaadiDao
import com.droidtechlab.shaaditestui.data.db.entities.ShaadiMatchListEntity
import com.droidtechlab.shaaditestui.data.db.entities.converters.*

const val DB_VERSION = 1
const val TABLE_SHAADI_MATCH_ENTITY = "table_shaadi_match_entity"
const val ID = "_id"
const val MATCH_STATE = "match_state"

@Database(entities = [ShaadiMatchListEntity::class], version = DB_VERSION)
@TypeConverters(
    DOBEntityConverter::class,
    LocationEntityConverter::class,
    NameEntityConverter::class,
    PictureEntityConverter::class,
    StreetEntityConverter::class
)
abstract class DB : RoomDatabase() {
    abstract fun shaadiMatchListDao(): ShaadiDao
}