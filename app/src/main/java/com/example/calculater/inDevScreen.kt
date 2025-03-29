package com.example.calculater

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.calculater.ui.theme.DullGreen

@Composable
fun inDevScreen(innerPadding: PaddingValues, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        Row(modifier = Modifier.weight(0.1f).fillMaxSize()) {
            MenuDropdown(currentPage = "inDevScreen", navController = navController)
        }
        Box(modifier = Modifier.weight(0.9f), contentAlignment = Alignment.Center) {
            Text(text = "In Development",color = DullGreen, fontSize = 40.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
    }
}