package com.danielceinos.useragent

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
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
        with(context.packageManager) {
            val versionName = try {
                this.getPackageInfo(context.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                "nameNotFound"
            }
            val versionCode = try {
                this.getPackageInfo(context.packageName, 0).versionCode.toString()
            } catch (e: PackageManager.NameNotFoundException) {
                "versionCodeNotFound"
            }

            val appName = try {
                this.getApplicationInfo(context.packageName, 0).name
            } catch (e: PackageManager.NameNotFoundException) {
                "appNameNotFount"
            }

            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            val version = Build.VERSION.SDK_INT
            val versionRelease = Build.VERSION.RELEASE

            val installerName = this.getInstallerPackageName(context.packageName) ?: "StandAloneInstall"

            return "$appName / $versionName($versionCode); $installerName; ($manufacturer; $model; $version; $versionRelease)"
        }
    }
}