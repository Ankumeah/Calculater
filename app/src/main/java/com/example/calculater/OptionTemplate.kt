package com.example.calculater

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
// and while I'm at it, I'd also like to apologize for my terrible code,
// in truth this isn't my first android dev project but my rather my first android studio project.
// Everything you see here is the code I learned along the way so that's why there are many bad practices
// If I could rewrite it would have made it better although I'm not sure if that would have been good either

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionTemplate(option: String, navController: NavHostController, modifier: Modifier = Modifier, context: Context) {

    val metrics = when (option) {
        "Length" -> listOf("mm", "cm", "m", "km")
        "Capacity" -> listOf("ml", "cl", "l", "kl")
        "Weight" -> listOf("mg", "cg", "g", "kg")
        "Temperature" -> listOf("C", "F", "K")
        //"Volume" -> listOf(/*TODO*/)
        //"Area" -> listOf(/*TODO*/)
        else -> listOf("mm", "cm", "m", "km")
    }
    val inputSelectedMetric = remember { mutableStateOf(metrics[0]) }
    val outputSelectedMetric = remember { mutableStateOf(metrics[1]) }

    val inputText = remember { mutableStateOf("") }
    val outputText = remember { mutableStateOf("") }

fun calculateConversion() {
    val conversionMap = mapOf(
        "Length" to mapOf("mm" to 1.0, "cm" to 10.0, "m" to 1000.0, "km" to 1_000_000.0),
        "Capacity" to mapOf("ml" to 1.0, "cl" to 10.0, "l" to 1000.0, "kl" to 1_000_000.0),
        "Weight" to mapOf("mg" to 1.0, "cg" to 10.0, "g" to 1000.0, "kg" to 1_000_000.0),
        "Temperature" to mapOf("C" to 1.0, "F" to 1.8, "K" to 1.0)
    )

    try {
        val inputValue = inputText.value.toDoubleOrNull()
        if (inputValue == null || inputText.value.isEmpty()) {
            outputText.value = ""
            return
        }

        val map = conversionMap[option] ?: return
        val inputFactor = map[inputSelectedMetric.value] ?: 1.0
        val outputFactor = map[outputSelectedMetric.value] ?: 1.0

        val baseValue = inputValue * inputFactor
        val result = baseValue / outputFactor

        outputText.value = if (option == "Temperature") {
            when {
                inputSelectedMetric.value == "C" && outputSelectedMetric.value == "F" ->
                    ((inputValue * 9 / 5) + 32).toString()

                inputSelectedMetric.value == "F" && outputSelectedMetric.value == "C" ->
                    ((inputValue - 32) * 5 / 9).toString()

                inputSelectedMetric.value == "C" && outputSelectedMetric.value == "K" ->
                    (inputValue + 273.15).toString()

                inputSelectedMetric.value == "K" && outputSelectedMetric.value == "C" ->
                    (inputValue - 273.15).toString()

                else -> result.toString()
            }
        } else {
            result.toString()
        }
    } catch (e: Exception) {
        outputText.value = "Invalid Input"
    }
}


    @Composable
    fun DigitButton(modifier: Modifier = Modifier, text: String, color: Color = Color.DarkGray) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(color = color)
                .clickable {
                    when (text) {
                        "C" -> inputText.value = ""
                        "<" -> inputText.value = inputText.value.slice(0 until inputText.value.length - 1)
                        else -> {
                            if (inputText.value.length != 21) {
                                inputText.value += text
                            }
                            else {
                                Toast.makeText(context, "Cannot enter more then 20 characters", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    calculateConversion()
                },
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

    Column(modifier = modifier.fillMaxSize().background(color = DarkDarkGray)) {
        Row(modifier = Modifier.weight(0.1f).fillMaxSize()) {
            Box(
                modifier = Modifier.weight(0.25f).fillMaxSize().padding(start = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                MenuDropdown(navController = navController, currentPage = option, color = DullGreen, modifier = Modifier.fillMaxWidth())
            }
            Box(
                modifier = Modifier.weight(0.75f).fillMaxSize().padding(start = 30.dp, end = 5.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = option, color = DullGreen, fontSize = 40.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
            }
        }
        Column(modifier = Modifier
            .weight(0.9f)
            .fillMaxSize()) {
                Column(modifier = Modifier.weight(0.2f).fillMaxSize()) {
                    Box(modifier = Modifier.weight(0.4f).fillMaxSize(),
                        contentAlignment = Alignment.CenterStart) {
                        MetricSelectorDropdown(options = metrics, selectedOption = inputSelectedMetric, modifier = Modifier.padding(10.dp), function = { calculateConversion() })
                    }
                    Box(modifier = Modifier.weight(0.6f).fillMaxSize().padding(10.dp), contentAlignment = Alignment.BottomEnd) {
                        Text(text = inputText.value, textAlign = TextAlign.End, color = Color.White, fontSize = 24.sp, modifier = Modifier.fillMaxWidth())
                    }
                }

            HorizontalDivider(modifier = Modifier.background(color = Color.DarkGray))

            Column(modifier = Modifier.weight(0.2f).fillMaxSize()) {
                Box(modifier = Modifier.weight(0.4f).fillMaxSize(),
                    contentAlignment = Alignment.CenterStart) {
                    MetricSelectorDropdown(options = metrics, selectedOption = outputSelectedMetric, modifier = Modifier.padding(10.dp), function = { calculateConversion() })
                }
                Row(modifier = Modifier.weight(0.6f).fillMaxSize()) {
                    Text(text = outputText.value, textAlign = TextAlign.End, color = Color.White, fontSize = 24.sp, modifier = Modifier.fillMaxWidth())
                }
            }

            HorizontalDivider(modifier = Modifier.background(color = Color.DarkGray))

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
                        DigitButton(text = "C", color = DullGreen)
                    }
                    Box(modifier = Modifier.weight(0.5f).fillMaxSize()) {
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
        OptionTemplate(navController = rememberNavController(), option = "Capacity", context = LocalContext.current)
    }
}