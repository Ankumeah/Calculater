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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.calculater.ui.theme.DullRed
import java.math.BigDecimal

@Composable
fun Screen(history: MutableState<MutableList<String>>, modifier: Modifier = Modifier, context: Context, navController: NavHostController) {
    val equation = remember { mutableStateOf("") }

    fun BigDecimal.toIntOrString(): Any {
        return if (this.remainder(BigDecimal.ONE) == 0.0.toBigDecimal()) this.toBigInteger() else this.toString()
    }

    fun storeEquation(input: String) {
        when (input) {
            "=" -> {
                if ("+" in equation.value || "-" in equation.value || "*" in equation.value || "/" in equation.value) {
                    try {
                        val resultAsDouble = eval(equation.value).toBigDecimal()
                        val result = resultAsDouble.toIntOrString()
                        history.value.add(0, "${equation.value} = $result")

                        equation.value = result.toString()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Please enter an operator", Toast.LENGTH_SHORT).show()
                }
            }

            "C" -> equation.value = ""
            "<" -> equation.value = equation.value.slice(0 until equation.value.length - 1)
            else -> if (equation.value.length >= 180) Toast.makeText(context, "Equation length cannot exceed 180 characters", Toast.LENGTH_SHORT).show() else equation.value += input
        }
    }

    @Composable
    fun DigitButton(modifier: Modifier = Modifier, text: String, color: Color = Color.DarkGray) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { storeEquation(text) }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = color),
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

    Surface(color = DarkDarkGray, modifier = modifier.fillMaxSize()) {
        Column {
            Row(modifier = Modifier.weight(0.1f).fillMaxSize()) {
                    Box(modifier = Modifier.weight(0.25f).fillMaxSize().padding(start = 20.dp), contentAlignment = Alignment.CenterStart) {
                        MenuDropdown(navController = navController, currentPage = "Home", color = DullGreen, modifier = Modifier.fillMaxWidth())
                    }
                    Box(modifier = Modifier.weight(0.75f).fillMaxSize()) {}
            }
            Column(modifier = Modifier.weight(0.35f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .weight(1f),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = equation.value,
                        color = Color.White,
                        fontSize = 30.sp,
                        textAlign = TextAlign.End
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.background(Color.DarkGray))
            Row(modifier = Modifier
                .weight(0.55f)
                .padding(10.dp)) {
                Column(modifier = Modifier
                    .weight(0.75f)
                    .fillMaxSize()) {
                    Row(modifier = Modifier
                        .weight(0.8f)
                        .fillMaxSize()) {
                        val buttonList = listOf(
                            listOf("C", "7", "4", "1"),
                            listOf("<", "8", "5", "2"),
                            listOf("/", "9", "6", "3"),
                        )
                        for (column in buttonList) {
                            Column(modifier = Modifier
                                .weight(0.33f)
                                .fillMaxSize()
                            ) {
                                for (button in column) {
                                    Box(modifier = Modifier
                                        .weight(0.2f)
                                        .fillMaxSize(), contentAlignment = Alignment.Center) {
                                        when (button){
                                            "<" -> DigitButton(text = button, color = DullGreen)
                                            "/" -> DigitButton(text = button, color = DullGreen)
                                            "C" -> DigitButton(text = button, color = DullRed)
                                            else -> DigitButton(text = button)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Row(modifier = Modifier
                        .weight(0.2f)
                        .fillMaxSize()) {
                        Box(modifier = Modifier
                            .weight(0.66f)
                            .fillMaxSize(), contentAlignment = Alignment.Center) {
                            DigitButton(text = "0")
                        }
                        Box(modifier = Modifier
                            .weight(0.33f)
                            .fillMaxSize(), contentAlignment = Alignment.Center) {
                            DigitButton(text = ".")
                        }
                    }
                }
                Column(modifier = Modifier
                    .weight(0.25f)
                    .fillMaxSize()) {
                    Box(modifier = Modifier
                        .weight(0.2f)
                        .fillMaxSize(), contentAlignment = Alignment.Center) {
                        DigitButton(text = "*", color = DullGreen)
                    }
                    Box(modifier = Modifier
                        .weight(0.2f)
                        .fillMaxSize(), contentAlignment = Alignment.Center) {
                        DigitButton(text = "-", color = DullGreen)
                    }
                    Box(modifier = Modifier
                        .weight(0.3f)
                        .fillMaxSize(), contentAlignment = Alignment.Center) {
                        DigitButton(text = "+", color = DullGreen)
                    }
                    Box(modifier = Modifier
                        .weight(0.3f)
                        .fillMaxSize(), contentAlignment = Alignment.Center) {
                        DigitButton(text = "=", color = DullGreen)
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
//        val history = remember { mutableStateOf(mutableListOf("1", "2")) }
//        Screen(history, context = LocalContext.current, navController = navController)
//    }
//}
