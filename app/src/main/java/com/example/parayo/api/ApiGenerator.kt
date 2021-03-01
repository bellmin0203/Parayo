package com.example.parayo.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiGenerator {

    fun <T> generate(api: Class<T>): T = Retrofit.Builder()
        .baseUrl(HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient())
        .build()
        .create(api)

    // Retrofit과 연계할 HTTP 통신 객체를 생성하는 함수입니다.
    private fun httpClient() =
        OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor()) // API의 응답 결과를 로그로 확인하기 위해 별도 HTTP 바디를 로깅해주도록 설정
        }.build()

    private fun httpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    companion object {
        const val HOST = "http://10.0.2.2:8000" // build variants 등을 이용해서 개발환경, 운영환경의 빌드 설정에 따라 자동으로 주소를 선택하게 만들어주는 편이 운영환경에 개발서버 주소를 입력하는 실수 등을 방지 가능
    }
}