package com.example.calculater

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
fun HistoryScreen(modifier: Modifier = Modifier, history: MutableState<MutableList<String>>, navController: NavHostController) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = DarkDarkGray)
    ) {
        Row(modifier = Modifier.weight(0.1f).fillMaxSize().fillMaxSize()) {
            Box(modifier = Modifier.weight(0.25f).fillMaxSize().padding(start = 20.dp), contentAlignment = Alignment.CenterStart) {
                MenuDropdown(navController = navController, currentPage = "History", color = DullGreen, modifier = Modifier.fillMaxWidth())
            }
            Box(modifier = Modifier.weight(0.5f).fillMaxSize().padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {
                Text(text = "History", color = DullGreen, fontSize = 35.sp)
            }
            Box(modifier = Modifier.weight(0.25f).fillMaxSize().padding(end = 20.dp), contentAlignment = Alignment.CenterEnd) {
                Image(
                    painter = painterResource(id = R.drawable.clear),
                    contentDescription = "Clear",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            history.value = mutableListOf<String>()
                        }
                )
            }
        }
        LazyColumn(modifier = Modifier.weight(0.9f)) {
            items(history.value) { text ->
                Text(
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    CalculaterTheme {
//        val navController = rememberNavController()
//        val fakeHistory = remember { mutableStateOf(mutableListOf("2=2", "2+2=21")) }
//        HistoryScreen(history = fakeHistory, navController = navController)
//    }
//}
