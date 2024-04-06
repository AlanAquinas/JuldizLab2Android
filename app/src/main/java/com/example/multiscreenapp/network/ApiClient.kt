import com.example.multiscreenapp.network.CelebrityService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"
    private const val API_KEY = "J0Lmm9tXRcXgoKQkNCjnZa3ILz8YKqmpLWFfYhpd"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()

            val newRequest = request.newBuilder()
                .addHeader("X-Api-Key", API_KEY)
                .build()
            chain.proceed(newRequest)
        }
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance = retrofit.create(CelebrityService::class.java)
}
