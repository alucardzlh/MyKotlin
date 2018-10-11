package com.zhirong.mykotlin.Activitys

import android.os.Build
import android.os.Bundle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import com.zhirong.mykotlin.Entitys.testlogin
import com.zhirong.mykotlin.Http.ApiClient
import com.zhirong.mykotlin.Http.ApiErrorModel
import com.zhirong.mykotlin.Http.ApiResponse
import com.zhirong.mykotlin.Http.NetworkScheduler
import com.zhirong.mykotlin.R
import com.zhirong.mykotlin.Utils.MyUtil

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiClient.instance.service.testlogin(MyUtil.MD5s(""+ Build.MODEL)!!,"","true",Build.MODEL,"abc")
                .compose(NetworkScheduler.compose())
                .bindUntilEvent(this,ActivityEvent.DESTROY)
                .subscribe(object : ApiResponse<testlogin>(this) {
                    override fun success(data: testlogin) {
                    }



                    override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) {
                    }

                })




    }
}


