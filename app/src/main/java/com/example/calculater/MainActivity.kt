package com.example.calculater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculater.ui.theme.CalculaterTheme

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

@Composable
fun MainScreen(innerPadding: PaddingValues = PaddingValues(start = 0.dp, top = 24.dp, end = 0.dp, bottom = 0.dp)) {
    val navController = rememberNavController()
    AppNavHost(navController)
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val history = remember { mutableStateOf(mutableListOf<String>()) }
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") { Screen(history =  history, context = LocalContext.current, navController = navController) }
        composable("History") { HistoryScreen(history = history, navController = navController) }
        composable("Options") {Screen(history =  history, context = LocalContext.current, navController = navController) }
        composable("About Us") {Screen(history =  history, context = LocalContext.current, navController = navController) }
    }
}

fun NavController.goTo(destination: String) {
    navigate(destination)
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    CalculaterTheme {
//        MainScreen()
//    }
//}
