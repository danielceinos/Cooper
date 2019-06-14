package com.danielceinos.useragent

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()

        builder.header("User-Agent", buildUserAgent(context))
    }

    private fun buildUserAgent(context: Context):String {
        val versionName = context.packageManager.getPackageInfo(this.context.packageName, 0).versionName
        val versionCode = this.context.packageManager.getPackageInfo(this.context.packageName, 0).versionCode
    }
}