package com.zhirong.mykotlin.Http

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 描述：线程周期
 * @author 章龙海.
 * @date 2018/9/30 17:06.
 */
object NetworkScheduler {
    fun<T> compose(): ObservableTransformer<T,T>{
        return ObservableTransformer {
            flowable->
            flowable.subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
//            observable ->
//            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}