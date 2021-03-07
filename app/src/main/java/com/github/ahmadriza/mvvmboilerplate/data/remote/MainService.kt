package com.github.ahmadriza.mvvmboilerplate.data.remote

import com.github.ahmadriza.mvvmboilerplate.models.*
import retrofit2.Response
import retrofit2.http.*

interface MainService {

    @POST("api/customer/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/customer/register")
    suspend fun register(@Body request: RegisterRequest): Response<BaseResponse<User>>

    @POST("api/customer/logout")
    fun logout(): Response<BaseResponse<Any>>

    @POST("api/customer/forgot-password")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Response<BaseResponse<Any>>

    @GET("api/customer/service")
    suspend fun getAllServices(): Response<BaseResponse<List<Product>>>

    @POST("/api/customer/checkout")
    fun checkOut(@Body request: ProductCheckoutRequest): Response<BaseResponse<Order>>

    @GET("/api/customer/my-order")
    fun getAllOrder(): Response<BaseResponse<List<Order>>>

    @GET("/api/customer/my-active-order")
    fun getActiveAllOrder(): Response<BaseResponse<List<Order>>>

    @GET("/api/customer/my-order/{id}")
    fun getDetailOrder(@Path("id") id: String): Response<BaseResponse<OrderDetail>>

    @POST("/api/customer/feedback")
    fun orderFeedback(@Body request: OrderFeedbackRequest): Response<BaseResponse<Any>>

    @GET("api/customer/invoice")
    fun createTopUp(@Body request: TopUpRequest): Response<BaseResponse<Invoice>>

    @GET("api/customer/invoice")
    fun getProfile(): Response<BaseResponse<List<Invoice>>>

    @GET("/api/customer/profile")
    fun getActiveTopUp(): Response<BaseResponse<User>>

    @PUT("/api/customer/profile")
    fun editProfile(@Body request: RegisterRequest): Response<BaseResponse<User>>


}