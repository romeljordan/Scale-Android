package com.demo.app.core.design.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.demo.app.core.design.R

val poppinsFontFamily = FontFamily(
    listOf(
        Font(R.font.Poppins_Black, weight = FontWeight.Black),
        Font(R.font.Poppins_ExtraBold, weight = FontWeight.ExtraBold),
        Font(R.font.Poppins_SemiBold, weight = FontWeight.SemiBold),
        Font(R.font.Poppins_Bold, weight = FontWeight.Bold),
        Font(R.font.Poppins_Medium, weight = FontWeight.Medium),
        Font(R.font.Poppins_Regular, weight = FontWeight.Normal),
        Font(R.font.Poppins_Light, weight = FontWeight.Light),
        Font(R.font.Poppins_Thin, weight = FontWeight.Thin),
    )
)
