package com.zhirong.mykotlin.Utils

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.UnsupportedEncodingException
import java.net.URISyntaxException
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

/**
 * 描述：全局工具类
 * @author 章龙海.
 * @date 2018/9/30 17:31.
 */
object MyUtil {


    /***
     * 获得32位随机码
     * @return
     */
    fun getUUID(): String {
        return UUID.randomUUID().toString().trim { it <= ' ' }.replace("-".toRegex(), "")
    }


    /**
     * 1、判断SD卡是否存在
     * 2
     * 3
     */
    fun hasSdcard(): Boolean {
        val status = Environment.getExternalStorageState()
        return status == Environment.MEDIA_MOUNTED
    }


    /**
     * 获取指定文件大小(单位：字节)
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getFileSize(file: File?): Long {
        if (file == null) {
            return 0
        }
        var size: Long = 0
        if (file.exists()) {
            var fis: FileInputStream? = null
            fis = FileInputStream(file)
            size = fis.available().toLong()
        }
        return size
    }


    /**
     * 获取文件真实路径
     * @param context
     * @param uri
     * @return
     * @throws URISyntaxException
     */
    fun getPath(context: Context, uri: Uri): String? {
        if ("content".equals(uri.scheme!!, ignoreCase = true)) {
            val projection = arrayOf("_data")
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(uri, projection, null, null, null)
                val column_index = cursor!!.getColumnIndexOrThrow("_data")
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index)
                }
            } catch (e: Exception) {
                Log.d("e", e.toString() + "")
                // Eat it  Or Log it.
            }

        } else if ("file".equals(uri.scheme!!, ignoreCase = true)) {
            return uri.path
        }
        return null
    }


    /**
     * 将bitmap转换成base64字符串
     * @param bitmap
     * @return base64 字符串
     */
    fun bitmaptoString(bitmap: Bitmap, bitmapQuality: Int): String? {

        // 将Bitmap转换成字符串
        var string: String? = null
        val bStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, bitmapQuality, bStream)
        val bytes = bStream.toByteArray()
        string = Base64.encodeToString(bytes, Base64.DEFAULT)
        return string
    }

    /**
     * 将base64转换成bitmap图片
     * @param string base64字符串
     * @return bitmap
     */
    fun stringtoBitmap(string: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val bitmapArray: ByteArray
            bitmapArray = Base64.decode(string, Base64.DEFAULT)
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.size)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }

    fun getBitmap(imgBase64Str: String): Bitmap? {
        try {
            val bitmapArray: ByteArray
            bitmapArray = Base64.decode(imgBase64Str, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }


    /**
     * MD5加密
     * @param
     * @return
     */
   final fun MD5s(s: String): String? {
        try {
            val mdInst = MessageDigest.getInstance("MD5")
            val btInput = s.toByteArray(charset("UTF-8"))
            mdInst.update(btInput)
            val md = mdInst.digest()
            val sb = StringBuffer()
            for (i in md.indices) {
                val `val` = md[i].toInt() and 0xff
                if (`val` < 16)
                    sb.append("0")
                sb.append(Integer.toHexString(`val`))
            }
            return sb.toString()
        } catch (e: UnsupportedEncodingException) {
            return ""
        }

    }


    /**
     * Cofox 日期函数
     * created at 2017/12/19 0:06
     * 功能描述：返回当前日期，格式：2017-12-19 12:13:55
     * file:cofoxFuction.kt
     *
     *
     * 修改历史：
     * 2017/12/19:新建
     *
     */
    fun getNow(): String {
        if (android.os.Build.VERSION.SDK_INT >= 24){
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        }else{
            var tms = Calendar.getInstance()
            return tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH).toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString() + " " + tms.get(Calendar.HOUR_OF_DAY).toString() + ":" + tms.get(Calendar.MINUTE).toString() +":" + tms.get(Calendar.SECOND).toString() +"." + tms.get(Calendar.MILLISECOND).toString()
        }

    }
}