package com.example.parayo.signup

import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.example.parayo.R
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignupActivityUI(
    private val viewModel: SignupViewModel // SignupActivityUI는 SignupViewModel의 데이터에 의존적이기 때문에 생성자에서 SignupViewModel을 받음
) : AnkoComponent<SignupActivity> {

    override fun createView(ui: AnkoContext<SignupActivity>) =
        ui.linearLayout { // 최상위 컨테이너로 LinearLayout을 생성
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL // LinearLayout의 높이가 자식 요소들이 차지하는 높이의 합보다 큰 경우에 한해 자식 요소들을 세로 중앙에 배치
            padding = dip(20)


            textView("회원가입") {
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textSize = 20f // 텍스트의 폰트 크기를 20SP로 설정
                typeface = Typeface.DEFAULT_BOLD
                textColorResource = R.color.colorPrimary
            }.lparams(width = matchParent) {
                bottomMargin = dip(50)
            }


            textInputLayout { // 4
                textInputEditText {
                    hint = "Email"
                    setSingleLine() // 줄바꿈을 허용하지 않도록 설정
                    bindString(ui.owner, viewModel.email) // AnkoMVVM에서 제공하는 데이터 바인딩 함수
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }


            textInputLayout {
                textInputEditText {
                    hint = "Name"
                    setSingleLine()
                    bindString(ui.owner, viewModel.name)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }

            textInputLayout {
                textInputEditText {
                    hint = "Password"
                    setSingleLine()
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    bindString(ui.owner, viewModel.password)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }

            button("회원가입") { // 5
                onClick { viewModel.signup() } // 이 람다 블록은 코루틴으로 실행되기 때문에 viewModel.signup()은 suspend 함수로 선언
            }.lparams(width = matchParent)
        }
}
