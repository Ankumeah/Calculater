package com.example.calculater

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.calculater.ui.theme.CalculaterTheme
import com.example.calculater.ui.theme.DarkDarkGray
import com.example.calculater.ui.theme.DullGreen
import kotlin.contracts.contract
import androidx.core.net.toUri

@Composable
fun AboutUsScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(DarkDarkGray)) {
        Row(modifier = Modifier
            .weight(0.1f)
            .fillMaxSize()
            .fillMaxSize()) {
            Box(modifier = Modifier
                .weight(0.25f)
                .fillMaxSize()
                .padding(start = 20.dp), contentAlignment = Alignment.CenterStart) {
                MenuDropdown(navController = navController, currentPage = "About Us", color = DullGreen, modifier = Modifier.fillMaxWidth())
            }
            Box(modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
                .padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {
                Text(text = "About Us", color = DullGreen, fontSize = 35.sp)
            }
            Box(modifier = Modifier
                .weight(0.25f)
                .fillMaxSize()
                .padding(end = 20.dp), contentAlignment = Alignment.CenterEnd) {}
        }
        Column(modifier = Modifier
            .weight(0.9f)
            .fillMaxSize()
            .padding(20.dp)) {
            Text(text = "Background -", color = DullGreen, fontSize = 30.sp)
            Spacer(Modifier.height(20.dp))
            Text(text = "BitCal is a small projects made by 2 high school students looking to get into tech and so BitCal was made for educational purposes",
                color = Color.White, fontSize = 24.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(20.dp))
            Text(text = "The Team -", color = DullGreen, fontSize = 30.sp)
            Spacer(Modifier.height(20.dp))
            Row {
                Column {
                    Text(text = "Programmer", color = Color.White, fontSize = 24.sp)
                    Text(text = "Designer", color = Color.White, fontSize = 24.sp)
                }
                Column {
                    Text(text = " :", color = Color.White, fontSize = 24.sp)
                    Text(text = " :", color = Color.White, fontSize = 24.sp)
                }
                Spacer(Modifier.size(width = 20.dp, height = 0.dp))
                Column {
                    val context = LocalContext.current
                    Text(text = "ankumeah", color = Color.White, fontSize = 24.sp, textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable{
                            try {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    "https://github.com/ankumeah/".toUri()
                                )
                                context.startActivity(intent)
                            }
                            catch (e: Exception) {
                                Toast.makeText(context, "Failed to open link", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        })
                    Text(text = "Decian Studios", color = Color.White, fontSize = 24.sp, textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CalculaterTheme {
        val navController = rememberNavController()
        AboutUsScreen(navController = rememberNavController())
    }
}