package com.droidtechlab.shaaditestui.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.droidtechlab.shaaditestui.data.db.TABLE_SHAADI_MATCH_ENTITY
import com.droidtechlab.shaaditestui.data.db.entities.converters.Converters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_SHAADI_MATCH_ENTITY)
data class ShaadiMatchListEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var _id: Long,
    @ColumnInfo(name = "gender") val gender: String? = null,
    @ColumnInfo(name = "email") val email: String? = null,
    @ColumnInfo(name = "phone") val phone: String? = null,
    @ColumnInfo(name = "cell") val cell: String? = null,
    @ColumnInfo(name = "name") @TypeConverters(Converters::class) val name: NameEntity? = null,
    @ColumnInfo(name = "picture") @TypeConverters(Converters::class) val picture: PictureEntity? = null,
    @ColumnInfo(name = "dob") @TypeConverters(Converters::class) val dob: DOBEntity? = null,
    @ColumnInfo(name = "registered") @TypeConverters(Converters::class) val registered: DOBEntity? = null,
    @ColumnInfo(name = "location") @TypeConverters(Converters::class) val location: LocationEntity? = null,
    @ColumnInfo(name = "match_state") val matchState: Int = -1,
) : Parcelable


