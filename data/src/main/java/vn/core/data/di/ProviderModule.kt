package vn.core.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.core.data.Config
import vn.core.data.network.NullOrEmptyConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val file = File(context.cacheDir, Config.CACHE_FILE_NAME)
        val isSuccess = file.mkdirs()
        return if (isSuccess) {
            Cache(file, Config.CACHE_FILE_SIZE)
        } else Cache(context.cacheDir, Config.CACHE_FILE_SIZE)
    }

    @Provides
    @Singleton
    @AnoHttpLoggingInterceptor
    fun bindHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    inline fun <reified T> provideRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): T {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(NullOrEmptyConverterFactory())
            .addConverterFactory(gsonConverterFactory).build()
        return retrofit.create(T::class.java)
    }
}