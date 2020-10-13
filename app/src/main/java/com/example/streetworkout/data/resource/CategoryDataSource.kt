package com.example.streetworkout.data.resource

import com.example.streetworkout.data.model.Category
import io.reactivex.rxjava3.core.Observable

interface CategoryDataSource {
    interface Local {
    }

    interface Remote {
        fun getInformationHome(): Observable<Category>
    }
}
