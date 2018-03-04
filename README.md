# Weather247-Mobile
[![API](https://img.shields.io/badge/API-19%2B-green.svg?style=flat-square)](https://android-arsenal.com/api?level=19)

![alt text](https://github.com/stefanusj/Weather247/blob/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png "Logo Application")

## Introduction 

Android Version of Weather247 App, this project was made by [me](https://github.com/stefanusj) and [my friend](https://github.com/aldidwiki)

------------------------------------------------
This app using Kotlin language, reference for language are [here](https://kotlinlang.org/docs/reference/)

## Libraries
This is a list of libraries used in this app
 - [Eclipse Paho Android Service](https://github.com/eclipse/paho.mqtt.android)
 - [Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
 - [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
 - [Retrofit](http://square.github.io/retrofit)
 - [RxAndroid](https://github.com/ReactiveX/RxAndroid)
 - [RxJava2Adapter](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2)

## Configuration 

Just set to your IP address or Website provider API, and it's done

**Path: ** [`Weather247/app/src/main/java/com/solusi247/weather247/service/ApiService.kt`](https://github.com/stefanusj/Weather247/blob/master/app/src/main/java/com/solusi247/weather247/service/ApiService.kt)

```Kotlin
interface ApiService {
    
    companion object {
        val BASE_URL = "your_provider_website_API"

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}

