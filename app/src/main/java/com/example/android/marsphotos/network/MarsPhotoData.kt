package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhotoData(
    val id: String,
    @Json(name = "img_src") val url: String
)