package com.example.calculater

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.calculater.ui.theme.CalculaterTheme
import com.example.calculater.ui.theme.DarkDarkGray
import com.example.calculater.ui.theme.DullGreen

// I'm really sorry for the redundant function but the other DigitButton
// could not be transported without giving it many extra argument,
// Im sure that it could have been resolved with some foresight which I was just incapable of.
// It's very hard to realise your mistakes when you are the only programmer in a project

@Composable
fun DigitButton(modifier: Modifier = Modifier, text: String, color: Color = Color.DarkGray) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {  }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(color = color, shape = RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .background(color = color)
            )
        }
    }
}

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
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = option, color = DullGreen, fontSize = 40.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
            }
        }
        Column(modifier = Modifier.weight(0.9f).fillMaxSize()) {
            Column(modifier = Modifier.weight(0.2f).fillMaxSize()) {

            }
            Column(modifier = Modifier.weight(0.2f).fillMaxSize()) {

            }
            Row(modifier = Modifier.weight(0.5f).fillMaxSize()) {
                Column(modifier = Modifier.weight(0.75f).fillMaxSize()) {
                    Column(modifier = Modifier.weight(0.75f).fillMaxSize()) {
                        val buttonList = listOf(
                            listOf("7", "8", "9"),
                            listOf("4", "5", "6"),
                            listOf("1", "2", "3")
                        )
                        for (i in buttonList) {
                            Row(modifier = Modifier.weight(0.33f).fillMaxSize()) {
                                for (button in i) {
                                    Box(modifier = Modifier.weight(0.33f).fillMaxSize()) {
                                        DigitButton(text = button)
                                    }
                                }
                            }
                        }
                    }
                    Row(modifier = Modifier.weight(0.25f).fillMaxSize()) {
                        Box(modifier = Modifier.weight(0.66f).fillMaxSize()) {
                            DigitButton(text = "0")
                        }
                        Box(modifier = Modifier.weight(0.33f).fillMaxSize()) {
                            DigitButton(text = ".")
                        }
                    }
                }
                Column(modifier = Modifier.weight(0.25f).fillMaxSize()) {
                    Box(modifier = Modifier.weight(0.5f).fillMaxSize()) {
                        DigitButton(text = "C", color = Color.Gray)
                    }
                    Box(modifier = Modifier.weight(0.5f).fillMaxSize()) {
                        DigitButton(text = "<", color = Color.Gray)
                    }
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
        OptionTemplate(navController = rememberNavController(), option = "Length")
    }
}