package com.example.calculater

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.calculater.ui.theme.CalculaterTheme
import com.example.calculater.ui.theme.DarkDarkGray
import com.example.calculater.ui.theme.DullGreen

@Composable
fun OptionTemplate(option: String, navController: NavHostController, modifier: Modifier = Modifier.fillMaxSize()) {
    Column(modifier = modifier.background(color = DarkDarkGray).padding(top = 24.dp)) {
        Row(modifier = Modifier.weight(0.1f).fillMaxSize().fillMaxSize()) {
            Box(
                modifier = Modifier.weight(0.25f).fillMaxSize().padding(start = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                MenuDropdown(
                    navController = navController,
                    currentPage = option,
                    color = DullGreen,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier.weight(0.75f).fillMaxSize().padding(start = 30.dp, end = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = option, color = DullGreen, fontSize = 40.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
            }
        }
        Column(modifier = Modifier.weight(0.9f).fillMaxSize()) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CalculaterTheme {
        val navController = rememberNavController()
        OptionTemplate(navController = rememberNavController(), option = "Length")
    }
}