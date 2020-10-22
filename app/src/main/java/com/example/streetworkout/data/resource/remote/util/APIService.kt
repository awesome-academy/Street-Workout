package com.example.streetworkout.data.resource.remote.util

import com.example.streetworkout.data.model.Category
import com.example.streetworkout.data.model.Exercise
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @GET("informationHome.php")
    fun getInformationHome(): Observable<Category>

    @FormUrlEncoded
    @POST("detailExercise.php")
    fun getListExercise(@Field("id") id: Int): Observable<List<Exercise>>
}
