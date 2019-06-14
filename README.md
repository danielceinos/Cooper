#User Agent Interceptor

This interceptor adds a usefull user agent value.

Following this format:

`User-Agent:Your awesome app name / 1.0.1(2830803); StandAloneInstall; (Google; Android SDK built for x86_64; SDK 28; Android 9)`


## Install

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
	        implementation 'com.github.danielceinos:User-Agent-Interceptor:X.Y.Z'
}
```
