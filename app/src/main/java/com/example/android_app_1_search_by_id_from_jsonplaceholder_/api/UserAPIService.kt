package com.example.android_app_1_search_by_id_from_jsonplaceholder_.api

interface UserAPIService {
    @GET("users")
    fun getUsers():Call<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: String):Call<User>
    companion object{
        val API_URL = "https://jsonplaceholder.typicode.com/"
        fun create(): UserAPIService {
            val retrofit =Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UserAPIService::class.java)
        }
    }

}