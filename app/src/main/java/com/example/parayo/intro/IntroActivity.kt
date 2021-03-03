package com.example.parayo.intro

import android.app.Activity
import android.os.Bundle
import com.example.parayo.signup.SignupActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class IntroActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IntroActivityUI().setContentView(this)

        GlobalScope.launch { // 메인 스레드에서 비동기 작업을 시작하고 코루틴 블록 내부에서 회원 가입 화면으로 전환을 시켜줌
            delay(1000) // 코루틴 내부에서 1초간 딜레이
            startActivity<SignupActivity>() // Anko에서 제공하는 헬퍼 함수로 새로운 Activity를 띄우는 코드를 간결한 문법으로 감싼 것
            finish() // IntroActivity는 뒤로 가기 버튼을 눌렀을 때 다시 보여지면 안되므로 finish()를 호출해 종료
        }
    }
}