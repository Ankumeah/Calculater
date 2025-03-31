package com.example.calculater

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.calculater.ui.theme.CalculaterTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.calculater.ui.theme.DarkDarkGray
import com.example.calculater.ui.theme.DullGreen

@Composable
fun OptionCard(modifier: Modifier = Modifier, text: String, image: Int = R.drawable.ic_launcher_foreground, navController: NavHostController) {
    Column(modifier = modifier
        .clip(shape = RoundedCornerShape(10.dp))
        .clickable {
            if (text.isNotEmpty()) {
                navController.goTo(text)
            }
        }) {
        Box(modifier = Modifier.weight(0.7f).fillMaxSize()) {
            Image(
                painter = painterResource(id = image),
                contentDescription = text,
                modifier = Modifier.fillMaxSize().padding(horizontal = 15.dp)
            )
        }
        Box(modifier = Modifier.weight(0.3f).fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = text, textAlign = TextAlign.Center, color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun OptionsScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().background(color = DarkDarkGray)) {
        Row(modifier = Modifier.weight(0.1f).fillMaxSize().fillMaxSize()) {
            Box(modifier = Modifier.weight(0.25f).fillMaxSize().padding(start = 20.dp), contentAlignment = Alignment.CenterStart) {
                MenuDropdown(navController = navController, currentPage = "Options", color = DullGreen, modifier = Modifier.fillMaxWidth())
            }
            Box(modifier = Modifier.weight(0.5f).fillMaxSize().padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {
                Text(text = "Options", color = DullGreen, fontSize = 35.sp)
            }
            Box(modifier = Modifier.weight(0.25f).fillMaxSize().padding(end = 20.dp), contentAlignment = Alignment.CenterEnd) {}
        }
        Column(modifier = Modifier.weight(0.9f).fillMaxSize().padding(20.dp)) {
            val options = listOf(
                listOf("Length", "Capacity", "Weight"),
                listOf("Temp", "Volume", "Area"),
                listOf()
            )
            for (i in options) {
                Row(modifier = Modifier.weight(1f/options.size).fillMaxSize()) {
                    for (option in i) {
                        Box(modifier = Modifier.weight(1f/i.size).fillMaxSize().padding(10.dp)) {
                            val image: Int = when (option) {
                                "Length" -> R.drawable.length
                                "Capacity" -> R.drawable.capacity
                                "Weight" -> R.drawable.weight
                                "Temp" -> R.drawable.temp
                                "Volume" -> R.drawable.volume
                                "Area" -> R.drawable.area
                                else -> R.drawable.ic_launcher_foreground
                            }
                            OptionCard(text = option, image = image, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    CalculaterTheme {
//        val navController = rememberNavController()
//        OptionsScreen(navController = rememberNavController())
//    }
//}