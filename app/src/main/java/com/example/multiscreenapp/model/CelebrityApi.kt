package com.example.multiscreenapp.model
import com.google.gson.annotations.SerializedName

data class CelebrityApi(
    val name: String,
    @SerializedName("net_worth") val netWorth: String,
    val gender: String,
    val nationality: String,
    val height: Double,
    val birthday: String,
    val occupation: ArrayList<String>? = null,
    val age: Int,
    @SerializedName("is_alive") val isAlive: Boolean,
) {
    companion object {
        fun toCelebrity(celebrityApi: CelebrityApi): Celebrity{
            println(celebrityApi)
            return Celebrity(
                name = celebrityApi.name,
                netWorth = celebrityApi.netWorth,
                gender = celebrityApi.gender,
                nationality = celebrityApi.nationality,
                height = celebrityApi.height,
                birthday = celebrityApi.birthday,
                occupation = celebrityApi.occupation ?: arrayListOf(),
                age = celebrityApi.age,
                isAlive = celebrityApi.isAlive,
            )
        }
    }
}
