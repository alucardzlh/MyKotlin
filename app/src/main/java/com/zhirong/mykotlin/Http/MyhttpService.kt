package com.zhirong.mykotlin.Http

import com.zhirong.mykotlin.Entitys.testlogin
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import io.reactivex.Observable

/**
 * 描述：网络请求接口地址参数类
 * @author 章龙海.
 * @date 2018/9/30 15:49.
 */
interface MyhttpService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<String>>

    @Multipart
    @POST("xxxx/xxxx") //This is imaginary URL
    fun updateImage(@Part("name") name: RequestBody,
                    @Part image: MultipartBody.Part): Observable<String>


    @GET("VersionInterface/queryVersionInfo")
    fun testlogin(@Query("token") token: String,
                  @Query("KeyNo") KeyNo: String,
                  @Query("dowmload") dowmload: String,
                  @Query("deviceId")deviceId:String,
                  @Query("appname")appname:String): Observable<testlogin>

//    @GET("xxx/xxx") //用于使用ResponseWrapper的举例，实际使用此API将无效
//    fun repos(@Path("user") user: String): Observable<ResponseWrapper<List<Repo>>> //使用ResponseWrapper时注意Observable尖括号里的内容
}