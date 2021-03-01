package com.example.parayo.api.response

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null
) {

    companion object {
        // reified 키워드는 인라인 함수에 붙을 수 있는 특별한 키워드로써 이 함수를 ApiResponse.error<Type>(...) 과 같은 형태로 호출할 수 있도록 만들어준다.
        inline fun <reified T> error(message: String? = null) =
            ApiResponse(false, null as T?, message)
    }
}