package com.example.classwork10.model

import com.google.gson.annotations.SerializedName

data class Task (
    @SerializedName("userId" ) var userId : Int?    = null,
    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("title"  ) var title  : String? = null,
    @SerializedName("completed"   ) var completed   : String? = null
)