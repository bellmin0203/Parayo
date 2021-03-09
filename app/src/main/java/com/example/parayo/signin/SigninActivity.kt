package com.example.parayo.signin

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class SigninActivity : BaseActivity<SigninViewModel>() {

    override val viewModelType = SigninViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SigninActivityUI(getViewModel()) // AnkoComponent의 setContentView() 함수는 액티비에 뷰를 적용시켜주는 역할
            .setContentView(this)
    }
}