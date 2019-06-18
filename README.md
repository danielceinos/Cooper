Cooper, interceptor for [OkHttp3](https://github.com/square/okhttp) to add bran new shine User-Agent header
--------

[![](https://jitpack.io/v/danielceinos/User-Agent-Interceptor.svg)](https://jitpack.io/#danielceinos/User-Agent-Interceptor)

This interceptor adds a useful User Agent header value.

Following this format:

`User-Agent:Your awesome app name / 1.0.1(2830803); StandAloneInstall; (Google; Android SDK built for x86_64; SDK 28; Android 9)`

AppName / VersionName(VersionCode); Installer package; (manufacturer; model; SDK version; Android version code)


Usage
--------

```kotlin
val client = OkHttpClient.Builder()
client.addInterceptor(CooperInterceptor(context))
```
Install
--------

Last release:

[![](https://jitpack.io/v/danielceinos/User-Agent-Interceptor.svg)](https://jitpack.io/#danielceinos/User-Agent-Interceptor)


Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency

```groovy
dependencies {
	        implementation 'com.github.danielceinos:Cooper:X.Y.Z'
}
```
