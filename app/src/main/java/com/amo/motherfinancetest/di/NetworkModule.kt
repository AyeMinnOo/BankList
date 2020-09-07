package com.amo.motherfinancetest.di

import com.amo.motherfinancetest.constants.ApiEndpoint
import com.amo.motherfinancetest.constants.Headers
import com.amo.motherfinancetest.datasource.pref.Pref
import com.amo.motherfinancetest.datasource.remote.ApiService
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(ApplicationComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(pref: Pref): OkHttpClient {
        val builder = OkHttpClient.Builder()
        try {

            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
            })
            val ssl = SSLContext.getInstance("TLSv1.2")
            ssl.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory = ssl.socketFactory
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { _, _ ->
                true
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        builder.addNetworkInterceptor { chain ->
            val requestOrigin = chain.request()
            val request = requestOrigin.newBuilder()
                .header("Connection", "close")
//                .header(Headers.APP_ID, pref.getAppId() ?: "")
                .header(Headers.APP_ID, "wnV24w-O8SJiHqk2DYzynz")
//                .header(Headers.SECRET_KEY, pref.getSecretKey() ?: "")
                .header(Headers.SECRET_KEY, "fq7emqsxGUdXvZ6ck2mcH6Tvf-GbUgZZlcB1UMKn7wb99ny")
                .method(requestOrigin.method, requestOrigin.body)
                .build()
            return@addNetworkInterceptor chain.proceed(request)
        }
            .addNetworkInterceptor(StethoInterceptor())
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiEndpoint.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(ApiService::class.java)
    }

}

