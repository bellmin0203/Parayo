package com.example.parayo.product.registration

import android.os.Bundle
import android.view.MenuItem
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductRegistrationActivity
    : BaseActivity<ProductRegistrationViewModel>() {

    override val viewModelType = ProductRegistrationViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductRegistrationUI(getViewModel())
            .setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "상품 등록" // 상단 액션바에 표시될 제목 텍스트
    }

    // android.R.id.home은 기본 액션바를 사용할 때 액션바 좌측의 버튼 아이디를 지칭
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(item.itemId) {
                android.R.id.home -> onBackPressed()
                else -> {}
            }
        }
        return true
    }
}