package com.example.parayo.product

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.parayo.R
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductMainActivity :
        BaseActivity<ProductMainViewModel>() {

    override val viewModelType = ProductMainViewModel::class

    private val ui by lazy { ProductMainUI(getViewModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        setupDrawerListener()
    }

    private fun setupDrawerListener() {
        // 툴바의 햄버거버튼과 드로어 레이아웃의 이벤트를 연결시켜주는 리스너 클래스
        val toggle = ActionBarDrawerToggle(
            this,
            ui.drawerLayout,
            ui.toolbar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
        ui.drawerLayout.addDrawerListener(toggle) // ActionBarDrawerToggle객체를 드로어 레이아웃의 이벤트 리스너로 등록

        toggle.syncState() // 현재 네비게이션 드로어의 상태와 햄버거버튼의 상태 동기화
    }
}