package com.example.streetworkout.util

import android.widget.EditText
import com.example.streetworkout.R
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding4.widget.afterTextChangeEvents
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Single
import java.lang.Exception
import java.util.concurrent.TimeUnit

fun EditText.observableInputNumber(
    min: Int,
    max: Int,
    error: String,
    textInputLayout: TextInputLayout
): Observable<String> =
    afterTextChangeEvents().skipInitialValue()
        .map {
            textInputLayout.error = null
            it.view.text.toString()
        }
        .debounce(1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .compose(transformObservable(min, max, error, context.getString(R.string.error_edit_text_empty)))
        .compose(retryWhenError {
            textInputLayout.error = it.message
        })

private fun transformObservable(min: Int, max: Int, error: String, errorDiff: String) =
    ObservableTransformer<String, String> { observable ->
        observable.flatMap { value ->
            Observable.just(value)
                .filter { it.toInt() in min..max }
                .singleOrError()
                .onErrorResumeNext {
                    if (it is NoSuchElementException) {
                        Single.error(Exception(error))
                    } else {
                        Single.error(Exception(errorDiff))
                    }
                }
                .toObservable()
        }
    }

private fun retryWhenError(onError: (Throwable) -> Unit) =
    ObservableTransformer<String, String> { observable ->
        observable.retryWhen { errors ->
            errors.flatMap {
                onError(it)
                Observable.just("")
            }
        }
    }
