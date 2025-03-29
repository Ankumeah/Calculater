package com.example.calculater

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
// and while I.m at it, I'd also like to apologize for my terrabel code,
// in truth this isn't my first android dev project but my rather my first android dev file.
// Everything you see here is the code I learned along the way so that's why there are many bad practices
// If I could rewrite it would have made it better although I'm not sure if that would have been good either

@Composable
fun DigitButton(modifier: Modifier = Modifier, text: String, color: Color = Color.DarkGray) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(color = color)
            .clickable {  },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionTemplate(option: String, navController: NavHostController, modifier: Modifier = Modifier) {

    val metrics = when (option) {
        "Length" -> listOf("mm", "cm", "m", "km")
        "Capacity" -> listOf("ml", "cl", "l", "kl")
        "Weight" -> listOf("mg", "cg", "g", "kg")
        "Temperature" -> listOf("C", "F", "K")
        "Volume" -> listOf(/*TODO*/)
        "Area" -> listOf(/*TODO*/)
        else -> listOf("mm", "cm", "m", "km")
    }
    val inputSelectedMetric = remember { mutableStateOf(metrics[0]) }
    val outputSelectedMetric = remember { mutableStateOf(metrics[1]) }

    Column(modifier = modifier
        .fillMaxSize()
        .background(color = DarkDarkGray)) {
        Row(modifier = Modifier
            .weight(0.1f)
            .fillMaxSize()
            .fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxSize()
                    .padding(start = 20.dp),
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
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 5.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = option, color = DullGreen, fontSize = 40.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
            }
        }
        Column(modifier = Modifier
            .weight(0.9f)
            .fillMaxSize()) {
                Column(modifier = Modifier
                    .weight(0.2f)
                    .fillMaxSize()) {
                    Box(modifier = Modifier
                        .weight(0.4f)
                        .fillMaxSize(),
                        contentAlignment = Alignment.CenterStart) {
                        MetricSelectorDropdown(options = metrics, selectedOption = inputSelectedMetric, modifier = Modifier.padding(10.dp))
                    }
                    Row(modifier = Modifier
                        .weight(0.6f)
                        .fillMaxSize()) {

                    }
                }

            HorizontalDivider(modifier = Modifier.background(color = Color.DarkGray))

            Column(modifier = Modifier
                .weight(0.2f)
                .fillMaxSize()) {
                Box(modifier = Modifier
                    .weight(0.4f)
                    .fillMaxSize(),
                    contentAlignment = Alignment.CenterStart) {
                    MetricSelectorDropdown(options = metrics, selectedOption = outputSelectedMetric, modifier = Modifier.padding(10.dp))
                }
                Row(modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize()) {

                }
            }

            HorizontalDivider(modifier = Modifier.background(color = Color.DarkGray))

            Row(modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()) {
                Column(modifier = Modifier
                    .weight(0.75f)
                    .fillMaxSize()) {
                    Column(modifier = Modifier
                        .weight(0.75f)
                        .fillMaxSize()) {
                        val buttonList = listOf(
                            listOf("7", "8", "9"),
                            listOf("4", "5", "6"),
                            listOf("1", "2", "3")
                        )
                        for (i in buttonList) {
                            Row(modifier = Modifier
                                .weight(0.33f)
                                .fillMaxSize()) {
                                for (button in i) {
                                    Box(modifier = Modifier
                                        .weight(0.33f)
                                        .fillMaxSize()) {
                                        DigitButton(text = button)
                                    }
                                }
                            }
                        }
                    }
                    Row(modifier = Modifier
                        .weight(0.25f)
                        .fillMaxSize()) {
                        Box(modifier = Modifier
                            .weight(0.66f)
                            .fillMaxSize()) {
                            DigitButton(text = "0")
                        }
                        Box(modifier = Modifier
                            .weight(0.33f)
                            .fillMaxSize()) {
                            DigitButton(text = ".")
                        }
                    }
                }
                Column(modifier = Modifier
                    .weight(0.25f)
                    .fillMaxSize()) {
                    Box(modifier = Modifier
                        .weight(0.5f)
                        .fillMaxSize()) {
                        DigitButton(text = "C", color = DullGreen)
                    }
                    Box(modifier = Modifier
                        .weight(0.5f)
                        .fillMaxSize()) {
                        DigitButton(text = "<", color = DullGreen)
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
        OptionTemplate(navController = rememberNavController(), option = "Capacity")
    }
}