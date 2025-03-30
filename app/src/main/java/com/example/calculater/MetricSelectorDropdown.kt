package com.example.calculater

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.calculater.ui.theme.DarkDarkGray

@Composable
fun MetricSelectorDropdown(options: List<String>, selectedOption: MutableState<String>, modifier: Modifier = Modifier, function: () -> Unit = {}) {
    val isExpanded = remember { mutableStateOf(false) }

    Box(modifier = modifier
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            isExpanded.value = true
        }) {
        Text(text = selectedOption.value,
            color = Color.White,
            fontSize = 30.sp,
        )
    }

    DropdownMenu(
        expanded = isExpanded.value,
        onDismissRequest = { isExpanded.value = false },
        modifier = Modifier.background(color = Color.DarkGray)
    ) {
        for (option in options) {
            DropdownMenuItem(
                text = { Text(text = option, color = Color.White) },
                onClick = {
                    isExpanded.value = false
                    selectedOption.value = option
                    function()
                }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    CalculaterTheme {
//        MetricSelectorDropdown(options = listOf("mm", "cm"), modifier = Modifier.size(200.dp))
//    }
//}