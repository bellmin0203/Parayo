package com.example.parayo.api

import com.example.parayo.api.request.SigninRequest
import com.example.parayo.api.request.SignupRequest
import com.example.parayo.api.response.ApiResponse
import com.example.parayo.api.response.SigninResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ParayoApi {

    // HTTP의 GET 메서드로 해당 URI를 호출한다는 것을 의미한다.
    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String> // Retrofit 2.6.0부터는 코루틴 지원, RxKotlin 등 별도 라이브러리 사용하지 않고도 비동기 호출을 지원한다.
                                             // 이를 이용하기 위해서 API 인터페이스를 suspend 함수로 선언해준다.

    companion object {
        // instance라는 정적 필드에 Retrofit이 생성해준 ParayoApi 인터페이스의 구현체를 넣어주었다.
        val instance = ApiGenerator()
            .generate(ParayoApi::class.java)
    }

    @POST("/api/v1/users") // HTTP의 POST 메서드 호출함
    suspend fun signup(@Body signupRequest: SignupRequest) // @Body 애노테이션은 파라미터의 값을 HTTP의 요청 본문에 쓰도록 지시시
       : ApiResponse<Void>

    @POST("/api/v1/signin")
    suspend fun signin(@Body signinRequest: SigninRequest)
    : ApiResponse<SigninResponse>
}