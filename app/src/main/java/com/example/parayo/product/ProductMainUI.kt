package com.example.parayo.product

import android.view.Gravity
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.parayo.R
import com.example.parayo.common.Prefs
import com.example.parayo.signin.SigninActivity
import com.example.parayo.view.borderBottom
import com.google.android.material.navigation.NavigationView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout

class ProductMainUI(
    private val viewModel: ProductMainViewModel
) : AnkoComponent<ProductMainActivity>,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<ProductMainActivity>) =
        ui.drawerLayout {
            drawerLayout = this

            verticalLayout {
                toolbar = toolbar {
                    title = "Parayo"
                    bottomPadding = dip(1) // 우측 메뉴 아이콘 영역이 툴바 전체 높이를 덮어버려 하단 배경이 잘리는 현상때문에
                    background = borderBottom(width = dip(1)) // 아래쪽 선이 들어간 배경을 지정
                    menu.add("Search")
                        .setIcon(R.drawable.ic_search)
                        .setShowAsAction(SHOW_AS_ACTION_ALWAYS)
                }.lparams(matchParent, wrapContent)
            }.lparams(matchParent, matchParent)

            // navigationView() 함수를 이용해 네비게이션 드로어를 생성
            navigationView = navigationView {
                ProductMainNavHeader()
                    .createView(AnkoContext.create(context, this)) // 별도 UI 컨텍스트에서 뷰 생성해 addHeaderView의 인자에 넘겨주는 방법
                    .run(::addHeaderView) // run { addHeaderView(this) }과 같이 람다를 넘겨줄 수도 있지만 파리미터가 하나일 경우 함수 레퍼런스를 넘기는 것도 허용됨

                // 4
                menu.apply {
                    add(NONE, MENU_ID_INQUIRY, NONE, "내 문의").apply {
                        setIcon(R.drawable.ic_chat)
                    }
                    add(NONE, MENU_ID_LOGOUT, NONE, "로그아웃").apply {
                        setIcon(R.drawable.ic_signout)
                    }
                }

                setNavigationItemSelectedListener(this@ProductMainUI)
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.START // drawerLayout의 안쪽에서 좌측 정렬
            }
        }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            MENU_ID_INQUIRY -> { viewModel.toast("내 문의")}
            MENU_ID_LOGOUT -> {
                Prefs.token = null
                Prefs.refreshToken = null
                viewModel.startActivityAndFinish<SigninActivity>()
            }
        }

        drawerLayout.closeDrawer(navigationView)

        return true
    }

    // 7
    companion object {
        private const val MENU_ID_INQUIRY = 1
        private const val MENU_ID_LOGOUT = 2
    }
}
