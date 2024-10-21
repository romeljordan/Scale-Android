package com.demo.app.core.design.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.demo.app.core.design.theme.appTypography

enum class MenuItem(
    val title: String
) {
    LogOut("Log out"),
    SortByNewest("Newest"),
    SortByFirst("First")
}

@Composable
fun DropDownMenu(
    options: List<MenuItem>, // TODO: change to immutable
    onDismiss: () -> Unit,
    onTapItem: (item: MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
    ) {
        DropdownMenu(
            modifier = Modifier.width(100.dp),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onDismiss.invoke()
            },
            containerColor = Color.White
        ) {
            options.forEach { option ->
                Box(
                    modifier = Modifier
                        .clickable { onTapItem.invoke(option) }
                        .padding(4.dp)
                ) {
                    Text(
                        text = option.title,
                        style = appTypography.labelSmall.copy(
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}
