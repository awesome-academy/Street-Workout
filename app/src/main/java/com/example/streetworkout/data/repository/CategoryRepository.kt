package com.example.streetworkout.data.repository

import com.example.streetworkout.data.model.Category
import com.example.streetworkout.data.resource.CategoryDataSource
import io.reactivex.rxjava3.core.Observable

class CategoryRepository (
    private val local: CategoryDataSource.Local,
    private val remote: CategoryDataSource.Remote
) : CategoryDataSource.Remote {

    override fun getInformationHome(): Observable<Category> = remote.getInformationHome()
}
