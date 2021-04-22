package com.droidtechlab.shaaditestui

interface RequestHandlerInterface {
    fun acceptRequest(id: Long)
    fun declineRequest(id: Long)
}