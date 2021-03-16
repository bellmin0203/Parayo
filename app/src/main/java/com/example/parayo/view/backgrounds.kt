package com.example.parayo.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable

private fun borderBG(
    borderColor: String = "#1F000000",
    bgColor: String = "#FFFFFF",
    borderLeftWidth: Int = 0,
    borderTopWidth: Int = 0,
    borderRightWidth: Int = 0,
    borderBottomWidth: Int = 0
): LayerDrawable {
    val layerDrawable = arrayOf<Drawable>(
        ColorDrawable(Color.parseColor(borderColor)), // 사각형 영역을 특정 색으로 채워주는 Drawable 클래스
        ColorDrawable(Color.parseColor(bgColor))
    ).let(::LayerDrawable) // LayerDrawable(...)과 같이 생성자를 호출하는 코드

    // 배경이 될 색상의 바깥쪽 여백 지정하는 방법으로 선 만듬
    layerDrawable.setLayerInset(
        1,
        borderLeftWidth,
        borderTopWidth,
        borderRightWidth,
        borderBottomWidth
    )

    return layerDrawable
}

fun borderLeft(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderLeftWidth = width
)

fun borderTop(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderTopWidth = width
)

fun borderRight(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderRightWidth = width
)

fun borderBottom(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderBottomWidth = width
)