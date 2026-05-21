package com.example.sokasinging.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun AppScaffold(
    title: String,
    onIconClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    isHome: Boolean,
    content : @Composable (PaddingValues) -> Unit

){
    Scaffold(
        topBar = {
            AppTopBar(
                title = title,
                onIconClick = onIconClick,
                isHome = isHome,
                onNotificationClick = onNotificationClick
            )
        },
        content = content
    )
}