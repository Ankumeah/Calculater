package com.example.calculater

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculater.ui.theme.CalculaterTheme
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculaterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(innerPadding: PaddingValues =  PaddingValues(start=0.0.dp, top=24.0.dp, end=0.0.dp, bottom=0.0.dp)) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val history = remember { mutableStateOf(mutableListOf<String>()) }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> Screen(history = history, modifier = Modifier.fillMaxSize().padding(innerPadding), LocalContext.current)
            1 -> HistoryScreen(texts = history.value, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun Screen(history: MutableState<MutableList<String>>, modifier: Modifier = Modifier, context : Context) {
    val equation = remember { mutableStateOf("") }

    fun eval(expression: String): Double {
        return ExpressionBuilder(expression).build().evaluate()
    }

    fun BigDecimal.toIntOrString(): Any {
        return if (this.remainder(BigDecimal.ONE) == 0.0.toBigDecimal()) this.toBigInteger() else this.toString()
    }

    fun storeEquation(input: String) {
        when(input) {
            "=" -> {
                if ("+" in equation.value || "-" in equation.value || "*" in equation.value || "/" in equation.value) {
                    try {
                        val resultAsDouble = eval(equation.value).toBigDecimal()
                        println(resultAsDouble)
                        val result = resultAsDouble.toIntOrString()
                        history.value.add("${equation.value} = $result")

                        equation.value = result.toString()
                    } catch (e: Exception) {
                        equation.value = "Error"
                    }
                }
                else {
                    Toast.makeText(context, "Please enter an operator", Toast.LENGTH_SHORT).show()
                }
            }
            "C" -> equation.value = ""
            "<" -> equation.value = equation.value.slice(0 until equation.value.length - 1)
            else -> if (equation.value.length >= 250) Toast.makeText(context, "Equation length cannot exceed 250 characters", Toast.LENGTH_SHORT).show() else equation.value += input
        }
    }

    @Composable
    fun DigitButton(modifier: Modifier = Modifier, name: String, color: Color = Color.DarkGray, textColor: Color = Color.White, enabled: Boolean = true){
        Button(onClick = {storeEquation(name)}, colors = ButtonDefaults.buttonColors(color), enabled = enabled, modifier = modifier) {Text(text = name, color = textColor, fontSize = 30.sp)}
    }

    Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
        Column {
            Column(modifier = Modifier.weight(0.5f)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .weight(1f)
                    , contentAlignment = Alignment.BottomEnd) {
                    Text(
                        text = equation.value,
                        color = Color.White,
                        fontSize = 30.sp,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider(modifier = Modifier.background(Color.DarkGray))
            }

            Row(modifier = Modifier.weight(0.5f)) {
                val complexList: List<List<List<Any>>> = listOf(
                    listOf(
                        listOf("C", Color.Red, true),
                        listOf("7", Color.DarkGray, true),
                        listOf("4", Color.DarkGray, true),
                        listOf("1", Color.DarkGray, true),
                        listOf(".", Color.DarkGray, true)
                    ),
                    listOf(
                        listOf("", Color.Black, false),
                        listOf("8", Color.DarkGray, true),
                        listOf("5", Color.DarkGray, true),
                        listOf("2", Color.DarkGray, true),
                        listOf("0", Color.DarkGray, true)
                    ),
                    listOf(
                        listOf("", Color.Black, false),
                        listOf("9", Color.DarkGray, true),
                        listOf("6", Color.DarkGray, true),
                        listOf("3", Color.DarkGray, true),
                        listOf("<", Color.DarkGray, true)
                    ),
                    listOf(
                        listOf("+", Color.Blue, true),
                        listOf("-", Color.Blue, true),
                        listOf("*", Color.Blue, true),
                        listOf("/", Color.Blue, true),
                        listOf("=", Color.Green, true)
                    )
                )
                for (column in complexList) {
                    Column(modifier = Modifier.weight(1f)) {
                        for (buttonData in column) {
                            val name = buttonData[0] as String
                            val color = buttonData[1] as Color
                            val enabled = buttonData[2] as Boolean

                            DigitButton(
                                name = name,
                                color = color,
                                enabled = enabled,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryScreen(modifier: Modifier = Modifier, texts: List<String>) {
    Column(modifier = Modifier.background(Color.Black)) {
        Spacer(modifier = Modifier.background(Color.Black).weight(0.025f))
        Column(modifier = Modifier.weight(0.05f)) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(Color.Black).padding(5.dp).background(Color.DarkGray, shape = RoundedCornerShape(10.dp))) {
                Text(text = "History", color = Color.White, fontSize = 20.sp)
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

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    CalculaterTheme {
        MainScreen()
    }
}