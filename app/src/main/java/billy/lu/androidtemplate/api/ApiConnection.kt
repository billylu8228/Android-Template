package billy.lu.androidtemplate.api

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

class ApiConnection {


    private val mService by lazy {
        getRetrofit().create(ApiService::class.java)
    }


    companion object {
        val instance = ApiConnection()

        private const val BASE_URL = "https://api.takeoutquick.com"
    }

//    suspend fun login(loginRequest: LoginRequest): BaseResponse<LoginResponse> {
//        return mService.login(loginRequest)
//    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getOkHttp())
            .build()
    }

    private fun getOkHttp(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()

            chain.proceed(newRequest)
        }
        okHttpBuilder.addInterceptor(interceptor)

        return okHttpBuilder.build()
    }









}