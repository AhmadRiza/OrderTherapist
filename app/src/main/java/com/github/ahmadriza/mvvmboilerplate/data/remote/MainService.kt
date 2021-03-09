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
    suspend fun logout(): Response<BaseResponse<Any>>

    @POST("api/customer/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<BaseResponse<Any>>

    @GET("api/customer/service")
    suspend fun getAllServices(): Response<BaseResponse<List<Product>>>

    @POST("api/customer/checkout")
    suspend fun checkOut(@Body request: ProductCheckoutRequest): Response<BaseResponse<Order>>

    @GET("api/customer/my-order")
    suspend fun getAllOrder(): Response<BaseResponse<List<Order>>>

    @GET("api/customer/my-active-order")
    suspend fun getActiveAllOrder(): Response<BaseResponse<List<Order>>>

    @GET("api/customer/my-order/{id}")
    suspend fun getDetailOrder(@Path("id") id: String): Response<BaseResponse<OrderDetail>>

    @POST("api/customer/feedback")
    suspend fun orderFeedback(@Body request: OrderFeedbackRequest): Response<BaseResponse<Any>>

    @POST("api/customer/invoice")
    suspend fun createTopUp(@Body request: TopUpRequest): Response<BaseResponse<Invoice>>

    @GET("api/customer/invoice")
    suspend fun getActiveTopUp(): Response<BaseResponse<Invoice>>

    @GET("api/customer/profile")
    suspend fun getProfile(): Response<BaseResponse<User>>

    @PUT("api/customer/profile")
    suspend fun editProfile(@Body request: EditUserRequest): Response<BaseResponse<User>>


}