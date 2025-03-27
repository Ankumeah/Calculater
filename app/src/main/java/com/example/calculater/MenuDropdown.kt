package com.example.calculater

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.calculater.ui.theme.DullGreen

@Composable
fun MenuDropdown(modifier: Modifier = Modifier, navController: NavHostController, currentPage: String, color: Color = Color.DarkGray) {
    var isRotated by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated) 90f else 0f,
        label = "rotation")

    Image(
        painter = painterResource(id = R.drawable.menu),
        contentDescription = "Menu",
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
            var image = painterResource(R.drawable.ic_launcher_foreground)
            when (option) {
                "Home" -> image = painterResource(R.drawable.caculator)
                "History" -> image = painterResource(R.drawable.history)
                "Options" -> image = painterResource(R.drawable.options)
                "About Us" -> image = painterResource(R.drawable.about_us)
                else -> Unit
            }
            if (option == currentPage) {Unit}
            else {
                DropdownMenuItem(
                    text = { Text(text = option, color = Color.White) },
                    leadingIcon = {
                        Icon(
                            painter = image,
                            contentDescription = option,
                            tint = DullGreen
                        )
                    },
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