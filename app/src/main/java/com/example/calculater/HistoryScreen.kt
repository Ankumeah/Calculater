package com.example.calculater

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HistoryScreen(modifier: Modifier = Modifier, texts: List<String>, navController: NavHostController) {
    Column(modifier = modifier.background(Color.Black)) {
        Spacer(modifier = Modifier
            .background(Color.Black)
            .weight(0.025f))
        Column(modifier = Modifier.weight(0.1f)) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(5.dp)
                    .background(Color.DarkGray, shape = RoundedCornerShape(5.dp))
            ) {
                Box(modifier = Modifier
                    .weight(0.2f)
                    .fillMaxSize()
                    .padding(10.dp), contentAlignment = Alignment.Center) {
                    MenuDropdown(navController = navController, modifier = Modifier.fillMaxSize(), currentPage = "History", color = Color.White)
                }
                Box(modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "History", color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
                Box(modifier = Modifier.weight(0.2f))
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(0.975f)
                .background(Color.Black),
        ) {
            items(texts) { text ->
                Text(
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(Color.DarkGray, shape = RoundedCornerShape(10.dp))
                        .padding(10.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}