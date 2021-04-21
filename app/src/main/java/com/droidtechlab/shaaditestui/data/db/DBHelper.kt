package com.droidtechlab.shaaditestui.data.db

import com.droidtechlab.shaaditestui.data.db.entities.*
import com.droidtechlab.shaaditestui.data.models.*

object DBHelper {

    @JvmStatic
    fun toMatchListEntity(shaadiResponse: MatchesList): ShaadiMatchListEntity {
        shaadiResponse.apply {
            return ShaadiMatchListEntity(
                _id = 0,
                gender = gender,
                name = toNameEntity(nameObject!!),
                email = email,
                phone = phone,
                cell = cell,
                picture = toPictureEntity(pictureObject!!),
                dob = toDobEntity(dobObject!!),
                location = toLocationEntity(locationObject!!),
                registered = toDobEntity(registeredObject!!) // Using same helper as both objects have same field
            )
        }
    }

    @JvmStatic
    fun toNameEntity(nameObject: NameObject): NameEntity {
        nameObject.apply {
            return NameEntity(
                title = title,
                first = first,
                last = last
            )
        }
    }

    @JvmStatic
    fun toPictureEntity(pictureObject: PictureObject): PictureEntity {
        pictureObject.apply {
            return PictureEntity(
                large = largeImage,
                medium = mediumImage,
                thumbnail = thumbnail
            )
        }
    }

    @JvmStatic
    fun toDobEntity(dobObject: DOBObject): DOBEntity {
        dobObject.apply {
            return DOBEntity(
                date = date,
                age = age
            )
        }
    }

    @JvmStatic
    fun toLocationEntity(locationObject: LocationObject): LocationEntity {
        locationObject.apply {
            return LocationEntity(
                city = city,
                state = state,
                country = country,
                postcode = postcode,
                street = toStreetEntity(street!!)
            )
        }
    }

    @JvmStatic
    fun toStreetEntity(streetObject: StreetObject): StreetEntity {
        streetObject.apply {
            return StreetEntity(
                name = name,
                number = number
            )
        }
    }
}