package com.zhirong.mykotlin.Entitys
import com.google.gson.annotations.SerializedName


/**
 * 描述：
 * @author 章龙海.
 * @date 2018/10/8 15:29.
 */

data class testlogin(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: Data,
    @SerializedName("version") val version: String
)

data class Data(
    @SerializedName("VersionInfo") val versionInfo: List<VersionInfo>
)

data class VersionInfo(
    @SerializedName("STATE") val sTATE: String,
    @SerializedName("NAME") val nAME: String,
    @SerializedName("VERSION") val vERSION: String,
    @SerializedName("REMARK") val rEMARK: String,
    @SerializedName("PATH") val pATH: String,
    @SerializedName("CREATETIME") val cREATETIME: String,
    @SerializedName("TYPE") val tYPE: String
)