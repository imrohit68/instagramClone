package com.example.instagram.models

data class posts (
    var description : String = "",
    var image_url : String  = "",
    var creation_time_milli : String = "",
    var User: User? = null
    )