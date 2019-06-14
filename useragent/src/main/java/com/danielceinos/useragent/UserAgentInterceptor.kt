package com.danielceinos.useragent

import android.content.Context
import android.content.pm.PackageManager
import okhttp3.Interceptor
import okhttp3.Response


class UserAgentInterceptor(private val context: Context) : Interceptor {

    private val userAgent: String by lazy {
        buildUserAgent(context)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("User-Agent", userAgent)
        return chain.proceed(builder.build())
    }

    private fun buildUserAgent(context: Context): String {
        val versionName = try {
            context.packageManager.getPackageInfo(this.context.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            "nameNotFound"
        }
        val versionCode = try {
            context.packageManager.getPackageInfo(this.context.packageName, 0).versionCode.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            "versionCodeNotFound"
        }

        val appName = try {
            context.packageManager.getApplicationInfo(this.context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            "appNameNotFount"
        }

        val installerName = context.packageManager.getInstallerPackageName(context.packageName)

        val agent = System.getProperty("http.agent")

        return "$appName / $versionName($versionCode); $installerName; $agent"
    }
}