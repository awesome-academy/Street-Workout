package com.example.streetworkout.data.resource.remote

import com.example.streetworkout.data.model.Category
import com.example.streetworkout.data.resource.CategoryDataSource
import com.example.streetworkout.data.resource.remote.util.APIService
import io.reactivex.rxjava3.core.Observable

class CategoryRemoteDataSource (
    private val apiService: APIService
) : CategoryDataSource.Remote {

    override fun getInformationHome(): Observable<Category> = apiService.getInformationHome()
}
