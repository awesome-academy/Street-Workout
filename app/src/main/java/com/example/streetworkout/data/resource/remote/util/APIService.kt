package com.example.streetworkout.data.resource.remote.util

import com.example.streetworkout.data.model.Category
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface APIService {
    @GET("informationHome.php")
    fun getInformationHome(): Observable<Category>
}
