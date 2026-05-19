package com.example.sokasinging.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sokasinging.R

@Composable
fun AppTopBar(
    onMenuClick: () -> Unit,
    onNotificationClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 22.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Hamburger
        IconButton(onClick = onMenuClick, modifier = Modifier.size(34.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Title
        Text(
            text = "The Gallery",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 24.sp,
            letterSpacing = (-1.2).sp,
            lineHeight = 32.sp,
            modifier = Modifier.weight(1f),
        )

        // Bell / notification
        IconButton(onClick = onNotificationClick, modifier = Modifier.size(36.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_outline_notifications),
                contentDescription = "Notifications",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}