package com.example.parayo.signup

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.parayo.api.ParayoApi
import com.example.parayo.api.request.SignupRequest
import com.example.parayo.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error

class SignupViewModel(app: Application): BaseViewModel(app) {

    val email = MutableLiveData("")
    val name = MutableLiveData("")
    val password = MutableLiveData("")

    suspend fun signup() {
        val request = SignupRequest(email.value, password.value, name.value)
        if(isNotValidSignup(request))
            return

        try {
            val response = requestSignup(request)
            onSignupResponse(response)
        } catch (e: Exception) {
            error("signup error", e) // error(string, throwable) 함수는 AnkoLogger를 상속받는 클래스에서 사용할 수 있는 Anko 라이브러리 함수, Log.e(tag, message, throwable) 같은 로깅 함수 대체, BaseViewModel에서 상속
            toast("알 수 없는 오류가 발생했습니다.") // 토스트 메시지를 쉽게 사용할 수 있도록 Anko에서 제공하는 헬퍼 함수, BaseViewModel에서 상속
        }
    }

    //
    private fun isNotValidSignup(signupRequest: SignupRequest) =
        when {
            signupRequest.isNotValidEmail() -> {
                toast("이메일 형식이 정확하지 않습니다.")
                true
            }
            signupRequest.isNotValidPassword() -> {
                toast("비밀번호는 8자 이상 20자 이하로 입력해주세요.")
                true
            }
            signupRequest.isNotValidName() -> {
                toast("이름은 2자 이상 20자 이하로 입력해주세요.")
                true
            }
            else -> false
        }

    // 회원 가입 API를 호출, 네트워크 요청 시에는 언제든 오류가 발생할 가능성이 존재하므로 바깥쪽에서 try-catch로 묶어 오류메시지 표시
    private suspend fun requestSignup(request: SignupRequest) =
        // withContext 코루틴 빌더를 이용하면 현재 스레드를 블록킹하지 않고 새로운 코루틴을 시작할 수 있음, 비동기로 실행되기때문에 코루틴 내부에서 실행되거나 suspend 함수 내부에서 실행되어야만 함
        withContext(Dispatchers.IO) {
            ParayoApi.instance.signup(request)
        }

    // requestSignup() 함수로부터 반환받은 응답을 넘겨 회원 가입 결과를 처리
    private fun onSignupResponse(response: ApiResponse<Void>) {
        if (response.success) {
            toast("회원 가입이 되었습니다. 로그인 후 이용해주세요.")
            finishActivity() // BaseViewModel에 미리 준비된 함수
        } else {
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }
}