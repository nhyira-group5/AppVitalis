package com.example.vitalisapp.Interface

import com.example.vitalisapp.Entity.Meta.ExibitionMeta
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiMeta {

    @GET("metas")
    suspend fun getMeta(): Response<List<ExibitionMeta>>

}