package com.danielceinos.cooper

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response


class CooperInterceptor(private val context: Context) : Interceptor {

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
                getPackageInfo(context.packageName, 0).versionName
            } catch (e: PackageManager.NameNotFoundException) {
                "nameNotFound"
            }
            val versionCode = try {
                getPackageInfo(context.packageName, 0).versionCode.toString()
            } catch (e: PackageManager.NameNotFoundException) {
                "versionCodeNotFound"
            }

            val applicationInfo = context.applicationInfo
            val stringId = applicationInfo.labelRes
            val appName =
                if (stringId == 0) applicationInfo.nonLocalizedLabel.toString()
                else context.getString(stringId)

            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            val version = Build.VERSION.SDK_INT
            val versionRelease = Build.VERSION.RELEASE

            val installerName = getInstallerPackageName(context.packageName) ?: "StandAloneInstall"

            return "$appName / $versionName($versionCode); $installerName; ($manufacturer; $model; SDK $version; Android $versionRelease)"
        }
    }
}