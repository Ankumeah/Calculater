package com.example.calculater

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MenuDropdown(modifier: Modifier = Modifier, navController: NavHostController, currentPage: String, color: Color = Color.DarkGray) {
    var isRotated by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated) 90f else 0f,
        label = "rotation")

    Text(
        text = "=",
        color = color,
        fontSize = 50.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth(0.15f)
            .graphicsLayer(rotationZ = rotationAngle)
            .clickable {
                isRotated = !isRotated
                isExpanded = true
            }
    )
    DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false; isRotated = false }, modifier = Modifier.background(color = Color.DarkGray)) {
        val options = listOf("Home", "History", "Options", "About Us")
        for (option in options) {
            if (option == currentPage) {Unit}
            else {
                DropdownMenuItem(
                    text = { Text(text = option, color = Color.White) },
                    onClick = {
                        isRotated = false
                        isExpanded = false
                        navController.goTo(option)
                    }
                )
            }
        }
    }
}